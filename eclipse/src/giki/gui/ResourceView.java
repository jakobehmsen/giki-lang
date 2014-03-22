package giki.gui;

import giki.parser.Resource;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.TabSet;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class ResourceView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResourceHolder resouceHolder;
	private Resource resource;
	
	private JTextPane textPaneIn;
	private JScrollPane textPaneInScrollPane;
	private boolean hasLoaded;
	
//	private boolean showLineNumbers;
	
	private EditorKit createEditorKit(final int defaultTabSize) {
		return new StyledEditorKit() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public ViewFactory getViewFactory() {
				final ViewFactory parentViewFactory = super.getViewFactory();
	            return new ViewFactory() {
					@Override
					public View create(Element elem) {
						String kind = elem.getName();
			            if (kind != null && kind.equals(AbstractDocument.ParagraphElementName)) {
			            	return new ParagraphView(elem) {
			            		public float nextTabStop(float x, int tabOffset) {
			                        TabSet tabs = getTabSet();
			                        if(tabs == null) {
			                            // a tab every 72 pixels.
			                            return (float)(getTabBase() + (((int)x / defaultTabSize + 1) * defaultTabSize));
			                        }
			             
			                        return super.nextTabStop(x, tabOffset);
			                    }
			            	};
			            }
			            
			            return parentViewFactory.create(elem);
					}
				};
			}
		};
	}

	public ResourceView(ResourceHolder resouceHolder, Resource resource) {
		this.resouceHolder = resouceHolder;
		this.resource = resource;
	}
	
	private void loadUI() {
		this.setLayout(new BorderLayout());
		
		textPaneInScrollPane = new JScrollPane();
		
		textPaneIn = new JTextPane();
		textPaneIn.setEditorKit(createEditorKit(32));
		
		textPaneIn.setFont(MainFrame.CODE_FONT);
		textPaneIn.setCaret(new javax.swing.text.DefaultCaret(){
			  /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void setSelectionVisible(boolean visible) {
				super.setSelectionVisible(true);
			}
		});
		
		JPanel textPaneInWrapper = new JPanel();
		textPaneInWrapper.setLayout(new BorderLayout());
		textPaneInWrapper.add(textPaneIn, BorderLayout.CENTER);
 
		textPaneInScrollPane.getViewport().add(textPaneInWrapper);
		textPaneInScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(textPaneInScrollPane, BorderLayout.CENTER);
	}
	
	private void loadResource() {
		try {
			InputStream inputStream = resource.getInputStream();
			Reader reader = new InputStreamReader(inputStream);
			Document textDocument = textPaneIn.getStyledDocument();
//			
//			try {
//				textDocument.remove(0, textDocument.getLength());
//			} catch (BadLocationException e3) {
//				e3.printStackTrace();
//			}
//			
//			boolean useSyntaxDecoration = false;
//			
//			if(useSyntaxDecoration) {
//				textDocument.addDocumentListener(new DocumentListener() {
//					Pattern syntaxIndicatorPattern = Pattern.compile("\\w+");
//					Pattern syntaxPattern = Pattern.compile("\\w+");
//					
//					SimpleAttributeSet attributeSetRegular = new SimpleAttributeSet();
//					SimpleAttributeSet attributeSetKeyword = new SimpleAttributeSet();
//					
//					{
//						StyleConstants.setForeground(attributeSetRegular, Color.BLACK);
//						StyleConstants.setForeground(attributeSetKeyword, Color.BLUE);
//					}
//					
//					@Override
//					public void removeUpdate(DocumentEvent e) {
//						invokeLaterSyntaxDecoration((StyledDocument)e.getDocument(), e.getOffset(), 1);
//					}
//	
//					@Override
//					public void insertUpdate(final DocumentEvent e) {
//						invokeLaterSyntaxDecoration((StyledDocument)e.getDocument(), e.getOffset(), e.getLength());
//					}
//	
//					private void invokeLaterSyntaxDecoration(final StyledDocument document, final int offset, final int length) {
//						SwingUtilities.invokeLater(new Runnable() {
//							@Override
//							public void run() {
//								int minKeywordSize = 3;
//								
//								int maxKeywordSize = 4;
//								int sampleRadiusLeft = maxKeywordSize;
//								int sampleRadiusRight = maxKeywordSize;
//								
//								int sampleOffset = Math.max(offset - sampleRadiusLeft, 0);
//								int sampleLength = Math.min(sampleRadiusLeft + length + sampleRadiusRight, document.getLength() - sampleOffset);
//								
//								if(sampleLength == 0)
//									return;
//								
//								try {
//									String sample = document.getText(sampleOffset, sampleLength);
//									
//									while(
//										(Character.isLetter(sample.charAt(0)) && sampleOffset != 0)
//										||
//										(Character.isLetter(sample.charAt(sample.length() - 1)) && sampleOffset + sampleLength < document.getLength())) {
//										
//										if(Character.isLetter(sample.charAt(0)))
//											sampleOffset = Math.max(sampleOffset - 1, 0);
//										if(Character.isLetter(sample.charAt(sample.length() - 1)))
//											sampleLength = Math.min(sampleLength + 1, document.getLength() - sampleOffset);
//										
//										sample = document.getText(sampleOffset, sampleLength);
//									}
//									
//									if(sample.length() >= minKeywordSize) {
//										Matcher keywordMatcher = syntaxPattern.matcher(sample);
//										
//										while(keywordMatcher.find()) {
//											String candidate = keywordMatcher.group();
//											
//											boolean isKeyword = 
//												candidate.equals("def") || candidate.equals("var") || candidate.equals("eof") || 
//												candidate.equals("rest") || candidate.equals("file");
//											
//											int candidateOffset = sampleOffset + keywordMatcher.start();
//											int candidateLength = candidate.length();
//											
//											if(isKeyword)
//												document.setCharacterAttributes(candidateOffset, candidateLength, attributeSetKeyword, true);
//											else
//												document.setCharacterAttributes(candidateOffset, candidateLength, attributeSetRegular, true);
//										}
//									}
//									else
//										document.setCharacterAttributes(offset, length, attributeSetRegular, true);
//								} catch (BadLocationException e1) {
//									e1.printStackTrace();
//								}
//							}
//						});
//					}
//					
//					@Override
//					public void changedUpdate(DocumentEvent e) { }
//				});
//			}
			DefaultCaret caret = (DefaultCaret) textPaneIn.getCaret();
			caret.setBlinkRate(366);
			int caretUpdatePolicy = caret.getUpdatePolicy();
			caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
			
			StringBuilder documentTextBuilder = new StringBuilder();
			char[] charBuffer = new char[1024];
			while(reader.ready()) {
				int readCount = reader.read(charBuffer, 0, charBuffer.length);
				documentTextBuilder.append(charBuffer, 0, readCount);
			}
			
			reader.close();
			
			try {
				textDocument.insertString(0, documentTextBuilder.toString(), null);
				
				textDocument.addDocumentListener(new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent e) {
						resouceHolder.resourceChanged();
					}

					@Override
					public void insertUpdate(final DocumentEvent e) {
						resouceHolder.resourceChanged();
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) { }
				});
			} catch (BadLocationException e2) {
				e2.printStackTrace();
			}
			
			textPaneIn.setDocument(textDocument);
			caret.setUpdatePolicy(caretUpdatePolicy);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveResource() throws IOException {
		if(hasLoaded) {
			OutputStream outputStream = resource.getOutputStream();
			Writer out = new OutputStreamWriter(outputStream);
			Document document = textPaneIn.getStyledDocument();
			try {
				String text = document.getText(0, document.getLength());
				out.append(text);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			out.close();
			outputStream.close();
		}
	}

	public String getText() {
		ensureLoaded();
		
		Document document = textPaneIn.getStyledDocument();
		String selectedText = textPaneIn.getSelectedText();
		if(selectedText != null)
			return selectedText;
		
		try {
			return document.getText(0, document.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		return null;
		
//		return textPaneIn.getText();
	}
	
	private void ensureLoaded() {
		if(!hasLoaded) {
			loadUI();
			loadResource();
			hasLoaded = true;
		}
	}

	public void beFocused() {
		ensureLoaded();
		
		textPaneIn.requestFocusInWindow();
	}
}
