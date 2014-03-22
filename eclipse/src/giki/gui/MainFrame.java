package giki.gui;

import giki.diagnostics.StopWatch;
import giki.parser.FileResourceStore;
import giki.parser.Module;
import giki.parser.ModuleApplication;
import giki.parser.ParseMessage;
import giki.parser.Parser;
import giki.parser.Resource;
import giki.parser.ResourceStore;
import giki.runtime.CodeBuilder;
import giki.runtime.Container;
import giki.runtime.IOBuffer;
import giki.runtime.Input;
import giki.runtime.Machine;
import giki.runtime.Output;
import giki.runtime.Symbol;
import giki.scheduling.Interruptable;
import giki.scheduling.Scheduler;
import giki.scheduling.SyncPoint;
import giki.scheduling.Scheduler.TaskNode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame {
	public static String NAME = "Giki"; 
	public static String VERSION = "0.0.3";
//	public static final String FONT_FAMILY = Font.MONOSPACED;

	public static final String FONT_FAMILY = "Consolas";
//	public static final String FONT_FAMILY = "Bitstream Vera Sans Mono";
//	public static final String FONT_FAMILY = "Courier";
//	public static final String FONT_FAMILY = "Courier New";
//	public static final String FONT_FAMILY = "DotumChe";
//	public static final String FONT_FAMILY = "GulimChe";
//	public static final String FONT_FAMILY = "Lucida Console";
//	public static final String FONT_FAMILY = "Lucida Sans Typewriter";
//	public static final String FONT_FAMILY = "MS Gothic";
//	public static final String FONT_FAMILY = "MS Mincho";
//	public static final String FONT_FAMILY = "DejaVu Sans Mono";
//	public static final String FONT_FAMILY = "Envy Code R";
	
	public static final Font CODE_FONT = new Font(FONT_FAMILY, Font.BOLD, 14);
	public static final Font TITLE_FONT = new Font(FONT_FAMILY, Font.BOLD, (int)(CODE_FONT.getSize() * 1.1));
	public static final Font DESCRIPTION_FONT = new Font(FONT_FAMILY, Font.BOLD, CODE_FONT.getSize());
	public static final Font ACTION_FONT = new Font(FONT_FAMILY, Font.BOLD | Font.ITALIC, CODE_FONT.getSize());
	public static final ImageIcon LOGO_ICON = new ImageIcon("res/logo-icon-32x32.png");

	private static final long serialVersionUID = 1L;
	
	private ResourceStoreProvider resourceStoreProvider;
	private JTabbedPane tabbedPane;
	private boolean hasChanges;
	private JButton resourceStoreButton;
	private JButton newResourceButton;
	private JPopupMenu popularResourceStoresPopupMenu;
	private TaskListView taskListView;
	private JSplitPane resourceViewTaskListSplitPane;

	public MainFrame(ResourceStoreProvider resourceStoreProvider) {
		this.resourceStoreProvider = resourceStoreProvider;
		this.setIconImage(LOGO_ICON.getImage());
		
		setStatus("");
		
		getContentPane().setLayout(new BorderLayout());
		
		JToolBar topLevelToolBar = createTopLevelToolBar();
		getContentPane().add(topLevelToolBar, BorderLayout.NORTH);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(TITLE_FONT);
		
//		tabbedPane.add("Dummy", new JLabel());
		
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(tabbedPane.getSelectedIndex() != -1) {
					JComponent documentContent = (JComponent)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
					if(documentContent != null)
						prepareResource(documentContent);
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				updateSelectedResourceStore();
			}
		});
		
		this.resourceStoreProvider.addListener(new ResourceStoreProvider.Listener() {
			@Override
			public void popularResourcesChanged(ResourceStoreProvider provider) {
				updatePopularResources();
			}
			
			@Override
			public void selectedResourceStoreChanged(ResourceStoreProvider provider) {
				updateSelectedResourceStore();
			}
		});
		
		taskListView = new TaskListView();
//		getContentPane().add(taskListView, BorderLayout.SOUTH);
		
		resourceViewTaskListSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, taskListView);
		resourceViewTaskListSplitPane.setResizeWeight(0.75);
		resourceViewTaskListSplitPane.setDividerLocation(0.90);
		
		getContentPane().add(resourceViewTaskListSplitPane, BorderLayout.CENTER);
	}
	
	private void updateSelectedResourceStore() {
		if(MainFrame.this.resourceStoreProvider.getSelectedResourceStore() != null) {
			newResourceButton.setVisible(true);
			FileResourceStore store = (FileResourceStore)resourceStoreProvider.getSelectedResourceStore();
			resourceStoreButton.setToolTipText(store.getDirectory());
			resourceStoreButton.setText(store.getName());
			taskListView.clear();
			tabbedPane.removeAll();
			tabbedPane.invalidate();
			tabbedPane.repaint();
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					loadViews();
					
//					if(tabbedPane.getSelectedIndex() != -1) {
//						JComponent documentContent = (JComponent)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
//						if(documentContent != null)
//							prepareResource(documentContent);
//					}
				}
			});
//			loadModules();
//			
//			if(tabbedPane.getSelectedIndex() != -1) {
//				JComponent documentContent = (JComponent)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
//				if(documentContent != null)
//					prepareResource(documentContent);
//			}
		} else {
			setStatus("Resource store needs to be selected");
			resourceStoreButton.setToolTipText("Click here to select a resource store.");
			newResourceButton.setVisible(false);
			ToolTipManager.sharedInstance().mouseMoved(
			        new MouseEvent(resourceStoreButton, 0, 0, 0,
			                0, resourceStoreButton.getHeight() / 4, // X-Y of the mouse for the tool tip
			                0, false));
		}
	}
	
	private String status = "";
	
	private void setStatus(String status) {
		if(!this.status.equals(status)) {
			this.status = status;
			updateTitleBar();
		}
	}
	
	private void updateTitleBar() {
		String statusText = status != null && status.length() > 0 ? " - " + status : "";
		String changesText = hasChanges ? "*" : "";
		setTitle(NAME + " " + VERSION + changesText + statusText);
	}

	private void loadViews() {
		setStatus("Loading...");
		
		try {
			resourceViewTaskListSplitPane.setTopComponent(null);
			
			ResourceStore store = resourceStoreProvider.getSelectedResourceStore();
			List<Resource> resources = store.getResources();

//			StopWatch sw = new StopWatch();
//			sw.start();
			for(Resource resource: resources) {
//				try {
//					InputStream resourceInputStream = store.getResource(name).getInputStream();
//					BufferedInputStream bufferedResourceInputStream = new BufferedInputStream(resourceInputStream);
					loadResourceView(resource);
//					bufferedResourceInputStream.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
			}
			
			if(tabbedPane.getTabCount() > 0)
				tabbedPane.setSelectedIndex(0);
			
			resourceViewTaskListSplitPane.setTopComponent(tabbedPane);
			
//			sw.stop();
//			sw.printElapsed(System.out, "Load resource views");
		}
		finally {
			setStatus("");
			setHasChanges(false);
		}
	}
	
	private void saveModules() {
		if(!hasChanges)
			return;
		
		try {
			setStatus("Saving...");
			for(int i = 0; i < tabbedPane.getTabCount(); i++) {
//				String name = tabbedPane.getTitleAt(i);
				
//				try {
//					ResourceStore store = resourceStoreProvider.getSelectedResourceStore();
//					OutputStream resourceOutputStream = store.getOutputStream(name);
//					BufferedOutputStream bufferedResourceOutputStream = new BufferedOutputStream(resourceOutputStream);
//					Writer writer = new OutputStreamWriter(bufferedResourceOutputStream);
					
					JComponent documentContent = (JComponent)tabbedPane.getComponentAt(i);
					saveDocument(documentContent);
					
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
		} finally {
			setStatus("");
		}
		
		setHasChanges(false);
	}

	private JToolBar createTopLevelToolBar() {
		JToolBar toolBar = new JToolBar();
		
		toolBar.setFloatable(false);

		resourceStoreButton = new JButton();
		popularResourceStoresPopupMenu = new JPopupMenu();
		
		resourceStoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(resourceStoreProvider.getPopularResourceStores().isEmpty()) {
					selectResourceStore();
				} else {
					popularResourceStoresPopupMenu.show((JButton)e.getSource(), 0, ((JButton)e.getSource()).getHeight());
				}
			}
		});
		
		updatePopularResources();

		toolBar.add(resourceStoreButton);
		toolBar.addSeparator();
		
		newResourceButton = addToolBarButton(toolBar, "New Resource", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newResource();
			}
		});
		
		return toolBar;
	}
	
	private void updatePopularResources() {
		List<ResourceStore> popularResourceStores = resourceStoreProvider.getPopularResourceStores();
		
		if(popularResourceStores.isEmpty()) {
			resourceStoreButton.setText("Select resource store...");
			resourceStoreButton.setFont(ACTION_FONT);
		} else {
			resourceStoreButton.setText(popularResourceStores.get(0).getName());
			resourceStoreButton.setFont(TITLE_FONT);
			
			popularResourceStoresPopupMenu.removeAll();
			
			for(final ResourceStore popularResourceStore: popularResourceStores.subList(1, popularResourceStores.size())) {
				JMenuItem usageMenuItem = popularResourceStoresPopupMenu.add("Change to " + popularResourceStore.getName());
				usageMenuItem.setToolTipText(((FileResourceStore) popularResourceStore).getDirectory());
				usageMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						resourceStoreProvider.selectResourceStore(popularResourceStore);
					}
				});
				usageMenuItem.setFont(ACTION_FONT);
			}
			
			JMenuItem selectResourceStoreMenutIem = popularResourceStoresPopupMenu.add("Change to other...");
			selectResourceStoreMenutIem.setFont(ACTION_FONT);
			
			selectResourceStoreMenutIem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectResourceStore();
				}
			});
		}
	}
	
	private void selectResourceStore() {
		FileResourceStore store = (FileResourceStore)resourceStoreProvider.getSelectedResourceStore();
		
		File file;
		String directory;
		if(store != null) {
			file = new File(store.getDirectory());
			directory = new File(store.getDirectory()).getParent();
		} else {
			file = null;
			directory = System.getProperty("user.dir");
		}
		
		JFileChooser fileModuleStoreChooser = new JFileChooser(directory);
		fileModuleStoreChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileModuleStoreChooser.setSelectedFile(file);
		fileModuleStoreChooser.setDialogTitle("Select Resource Store");
		fileModuleStoreChooser.setApproveButtonText("Select");
		fileModuleStoreChooser.setMultiSelectionEnabled(false);
		fileModuleStoreChooser.setApproveButtonToolTipText("Select resource store");
		if(fileModuleStoreChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				String newDirectory = fileModuleStoreChooser.getSelectedFile().getCanonicalPath();
				FileResourceStore newStore = new FileResourceStore(newDirectory);
				resourceStoreProvider.selectResourceStore(newStore);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private Scheduler scheduler = new Scheduler();

	private JToolBar createDocumentLevelToolBar() {
		JToolBar toolBar = new JToolBar();
		
		toolBar.setFloatable(false);
		
		addToolBarButton(toolBar, "Parse", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				documentParse();
			}
		});
		
		addInterruptableButton(toolBar, "Run", "Resume", "Finish", "Pause", new Interruptable.Factory() {
			@Override
			public Interruptable createInterruptable() {
				TaskProvider taskView = createDocumentRunTaskNode();
				
				Scheduler.TaskNode taskNode = taskView.createTaskRoot();
				
				Interruptable interruptable = scheduler.schedule(taskNode);
				taskView.setup(interruptable);
				return interruptable;
			}
		});
		
		addToolBarButton(toolBar, "Rename", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				documentRename();
			}
		});
		
		toolBar.addSeparator();
		
		addToolBarButton(toolBar, "Remove", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				documentDelete();
			}
		});
		
		return toolBar;
	}
	
	public static JButton addToolBarButton(JToolBar toolBar, String text, final ActionListener onClick) {
		JButton button = createButton(text, onClick);
		toolBar.add(text, button);
		return button;
	}
	
	private static JButton createButton(String text, final ActionListener onClick) {
		return createButton(text, ACTION_FONT, onClick);
	}
	
	private static JButton createButton(String text, Font font, final ActionListener onClick) {
		final JButton button = createButton(text, font);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(false);
				button.revalidate();
				button.repaint();
				try {
					onClick.actionPerformed(e);
				} finally {
					button.setEnabled(true);
				}
			}
		});
		return button;
	}
	
	private static JButton createButton(String text, final Font font) {
		final JButton button = new JButton(text);
		button.setFont(font);
		
		button.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if(e.getPropertyName().equals("enabled")) {
					if(button.isEnabled()) {
//						button.setFont(font);
						button.setForeground(Color.BLACK);
					} else {
//						button.setFont(new Font(font.getFontName(), font.getStyle(), 8));
						button.setForeground(Color.GRAY);
					}
				}
			}
		});
		
		return button;
	}
	
	public static void addInterruptableButton(JToolBar toolBar, final String executeText, final String resumeText, final String finishText, String interruptText, final Interruptable.Factory onExecute) {
		final JButton button = createButton(executeText, ACTION_FONT);
		toolBar.add(button);
		
		final JButton interruptButton = createButton(interruptText, ACTION_FONT);
		interruptButton.setEnabled(false);
		toolBar.add(interruptButton);
		
		final JButton finishButton = createButton(finishText, ACTION_FONT);
		finishButton.setEnabled(false);
		toolBar.add(finishButton);
		
		final Interruptable[] interruptableHolder = new Interruptable[1];
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(button.getText().equals(resumeText)) {
					interruptableHolder[0].resume();
					return;
				}
				
				final Interruptable interruptable = onExecute.createInterruptable();
				
				interruptableHolder[0] = interruptable;
				
				final ActionListener interruptClick = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						interruptable.stop();
					}
				};
				
				final ActionListener finishClick = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						interruptable.finish();
					}
				};
				
				interruptable.addListener(new Interruptable.Listener() {
					@Override
					public void stopped() {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								button.setEnabled(true);
								button.revalidate();
								button.repaint();
								interruptButton.setEnabled(false);
								interruptButton.revalidate();
								interruptButton.repaint();
							}
						});
					}
					
					@Override
					public void started() {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								button.setText(resumeText);
								finishButton.setEnabled(true);
								interruptButton.addActionListener(interruptClick);
								finishButton.addActionListener(finishClick);
							}
						});
						
						resumed();
					}
					
					@Override
					public void resumed() {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								button.setEnabled(false);
								button.revalidate();
								button.repaint();
								interruptButton.setEnabled(true);
								interruptButton.revalidate();
								interruptButton.repaint();
							}
						});
					}
					
					@Override
					public void finished() {
						stopped();
						
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								interruptButton.removeActionListener(interruptClick);
								interruptButton.removeActionListener(finishClick);
								finishButton.setEnabled(false);
								button.setText(executeText);
							}
						});
					}
				});
				
				interruptable.start();
			}
		});
	}
	
	private String getDefaultResourceName() {
		return resourceStoreProvider.getSelectedResourceStore().ensureUnique("workspace");
	}
	
	private void newResource() {
		String documentName = getDefaultResourceName();
		newResource(documentName);
	}
	
	private void newResource(String name) {
		try {
			Resource newResource = resourceStoreProvider.getSelectedResourceStore().create(name);
			JComponent documentContent = createDocumentContent(newResource);
			tabbedPane.add(name, documentContent);
			tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
			
			prepareResource(documentContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ResourceView createResourceView(Resource resource) {
		return new ResourceView(new ResourceHolder() {
			@Override
			public void resourceChanged() {
				setHasChanges(true);
			}
		}, resource);
	}
	
	private void loadResourceView(Resource resource) {
		ResourceView resourceView = createResourceView(resource);
//		InputStreamReader reader = new InputStreamReader(inputStream);
		JComponent documentContent = createDocumentContent(resourceView);
//		StopWatch sw = new StopWatch();
//		sw.start();
		tabbedPane.add(resource.getName(), documentContent);
//		sw.printElapsed(System.out, "loadResourceView");
//		resourceView.loadResource(reader);
	}
	
	private void prepareResource(JComponent documentContent) {
		ResourceView resourceView = (ResourceView)documentContent.getComponent(1);
		resourceView.beFocused();
	}
	
	private void setHasChanges(boolean hasChanges) {
		if(this.hasChanges == hasChanges)
			return;
		
		this.hasChanges = hasChanges;
		updateTitleBar();
	}
	
	private void saveDocument(JComponent documentContent) {
		ResourceView resourceView = (ResourceView)documentContent.getComponent(1);
		try {
			resourceView.saveResource();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private JComponent createDocumentContent(Resource resource) {
		ResourceView resourceView = createResourceView(resource);
		return createDocumentContent(resourceView);
	}
	
	private JComponent createDocumentContent(ResourceView resourceView) {
		JPanel tabContent = new JPanel();
		tabContent.setLayout(new BorderLayout());
		
		JToolBar toolBar = createDocumentLevelToolBar();
		tabContent.add(toolBar, BorderLayout.NORTH);
		
		tabContent.add(resourceView, BorderLayout.CENTER);
		
		return tabContent;
	}
	
	private ResourceStore toResourceStore() {
		return new ResourceStore() {
			@Override
			public String getResourcePath(String name) {
				return name;
			}
			
			@Override
			public boolean exists(String name) {
				return getTabIndexByName(name) != -1;
			}
			
			private int getTabIndexByName(String name) {
				for(int i = 0; i < tabbedPane.getTabCount(); i++) {
					if(tabbedPane.getTitleAt(i).equals(name))
						return i;
				}
				
				return -1;
			}

			@Override
			public String getName() {
				return null;
			}

			@Override
			public List<Resource> getResources() {
				ArrayList<Resource> resources = new ArrayList<Resource>();
				
				for(int i = 0; i < tabbedPane.getTabCount(); i++)
					resources.add(createResource(i));
				
				return resources;
			}

			@Override
			public Resource create(String name) throws IOException { 
				return null;
			}

			@Override
			public void remove(String name) throws IOException { }

			@Override
			public String ensureUnique(String name) {
				return null;
			}
			
			private Resource createResource(final int tabIndex) {
				final String name = tabbedPane.getTitleAt(tabIndex);
				
				return new Resource() {
					
					@Override
					public void rename(String newName) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public String getPath() {
						// TODO Auto-generated method stub
						return name;
					}
					
					@Override
					public OutputStream getOutputStream() throws IOException {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public String getName() {
						return name;
					}
					
					@Override
					public InputStream getInputStream() throws IOException {
						JPanel tabContent = (JPanel)tabbedPane.getComponentAt(tabIndex);
						ResourceView requestedResourceView = (ResourceView)tabContent.getComponent(1);
						String text = requestedResourceView.getText();
						return new ByteArrayInputStream(text.getBytes());
					}
				};
			}
			
			@Override
			public Resource getResource(final String name) {
				final int tabIndex = getTabIndexByName(name);
				
				return createResource(tabIndex);
			}
		};
	}
	
	private TaskProvider createDocumentRunTaskNode() {
		int tabIndex = tabbedPane.getSelectedIndex();
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		final PrintStream out = new PrintStream(os);
		final String name = tabbedPane.getTitleAt(tabIndex);
		final Output[] outputHolder = new Output[1];
		
		return new TaskProvider() {
			TaskView taskView;
			
			@Override
			public void setup(final Interruptable interruptable) {
				interruptable.addListener(new Interruptable.Listener() {
					@Override
					public void started() {
						interruptable.doBefore(new SyncPoint() {
							@Override
							public void doAndThenCall(final Runnable continuation) {
								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										taskView = MainFrame.this.taskListView.newTask(name);
										
										continuation.run();
									}
								});
							}
						});
					}

					@Override
					public void resumed() {
						interruptable.doBefore(new SyncPoint() {
							@Override
							public void doAndThenCall(final Runnable continuation) {
								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										taskView.setStatus("Running");
										
										continuation.run();
									}
								});
							}
						});
					}

					@Override
					public void stopped() {
						taskView.setStatus("Stopped");
					}

					@Override
					public void finished() {
						String print;
						try {
							print = os.toString("UTF8");
							taskView.setResult(print);
							taskView.end();
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			
			@Override
			public TaskNode createTaskRoot() {
				return new TaskNode() {
					@Override
					public TaskNode next(boolean finishRequested) {
						saveModules();
						taskView.setStatus("Parsing");
						
						ResourceStore resourceStore = toResourceStore();
						
						ModuleResolver moduleResolver = new ModuleResolver();
						
						try {
							moduleResolver.addModules(resourceStore);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
//						final ModuleApplication moduleApplication = new ModuleApplication(resourceStore, name);
						
						try {
							final CodeBuilder.Build entryPointBuild = moduleResolver.build(name);
							if(moduleResolver.getParseMessages().size() > 0) {
								out.println("Issues: ");
								for(int i = 0; i < moduleResolver.getParseMessages().size(); i++) {
									if(i > 0)
										out.println();
									ParseMessage message = moduleResolver.getParseMessages().get(i);
									out.print(message.location.resourceName + "(" + message.location.line + "," + message.location.column + "): " + message.text);
								}

								return TaskNode.End.INSTANCE;
							} else {
								taskView.setStatus("Running");

								Symbol[] variables = new Symbol[entryPointBuild.variableCount];
								
								final Input input = new Container.Default().createInput();
								final Output output = new Container.Default().createOutput();
								final Machine machine = new Machine(input, output, entryPointBuild.code, variables, entryPointBuild.interCount);
								
								return new TaskNode() {
									@Override
									public TaskNode next(boolean finishRequested) {
										try {
											if(finishRequested) {
												out.println(Machine.resultToString(machine.getResult()));
												
												outputHolder[0] = output;
												
												taskView.setOutput(output);
												
												return TaskNode.End.INSTANCE;
											}
											
											machine.evaluate();
											
											if(machine.getResult() != Machine.RESULT_ABSENT) {
												out.println(Machine.resultToString(machine.getResult()));
												
												outputHolder[0] = output;
												
												taskView.setOutput(output);
												
												return TaskNode.End.INSTANCE;
											} else {
												return this;
											}
										} catch(Error e) {
											out.println("Unexpected error: " + e.getMessage());
											return TaskNode.End.INSTANCE;
										}
									}
									
									@Override
									public boolean atEnd() {
										return false;
									}
								};
							}
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						
						return TaskNode.End.INSTANCE;
					}
					
					@Override
					public boolean atEnd() {
						return false;
					}
				};
			}
		};
	}

	private void documentParse() {
		saveModules();
		
		int tabIndex = tabbedPane.getSelectedIndex();
		String name = tabbedPane.getTitleAt(tabIndex);
		ResourceStore resourceStore = toResourceStore();
		Resource resource = resourceStore.getResource(name);
		Parser resourceParser = new Parser(System.out);
		try {
			Module module = resourceParser.toModule(resource);
			
			Symbol moduleAsSymbol = module.reify();
			TaskView taskView = taskListView.newTask(name);
			taskView.setStatus("Finished");
			taskView.setResult("Accepted");
			taskView.setOutput(new IOBuffer(Arrays.asList(moduleAsSymbol)));
			taskView.end();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void documentRename() {
		int tabIndex = tabbedPane.getSelectedIndex();
		String currentName = tabbedPane.getTitleAt(tabIndex);
		String newName = (String)JOptionPane.showInputDialog(null, "New name", "Rename", JOptionPane.PLAIN_MESSAGE, null, null, currentName);
		if(newName != null) {
			ResourceStore store = resourceStoreProvider.getSelectedResourceStore();
			try {
				store.getResource(currentName).rename(newName);
				tabbedPane.setTitleAt(tabIndex, newName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void documentDelete() {
		int tabIndex = tabbedPane.getSelectedIndex();
		String name = tabbedPane.getTitleAt(tabIndex);
		try {
			ResourceStore store = resourceStoreProvider.getSelectedResourceStore();
			store.remove(name);
			tabbedPane.remove(tabIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
