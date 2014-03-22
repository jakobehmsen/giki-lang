package giki.gui;

import giki.runtime.Output;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class TaskListView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel outputTableModel;
	private JTable outputTable;
	
	public TaskListView() {
		setLayout(new BorderLayout());
		
		outputTable = new JTable();
		outputTable.setFont(MainFrame.DESCRIPTION_FONT);
		outputTable.getTableHeader().setFont(MainFrame.TITLE_FONT);
		outputTableModel = (DefaultTableModel)outputTable.getModel();

		outputTableModel.addColumn("Resource");
		outputTableModel.addColumn("Status");
		outputTableModel.addColumn("Start");
		outputTableModel.addColumn("End");
		outputTableModel.addColumn("Result");
		outputTableModel.addColumn("Output");
		
		outputTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		DefaultTableColumnModel outputTableColumnModel = (DefaultTableColumnModel)outputTable.getColumnModel();
		
		for (int c = 0; c < outputTable.getColumnCount(); c++) {
			outputTableColumnModel.getColumn(c).setCellEditor(new DefaultCellEditor(new JTextField()) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(EventObject anEvent) {
					return false;
				}
			});
		}

		outputTableColumnModel.getColumn(0).setMaxWidth(150);
		outputTableColumnModel.getColumn(0).setPreferredWidth(150);
		outputTableColumnModel.getColumn(1).setMaxWidth(100);
		outputTableColumnModel.getColumn(1).setPreferredWidth(100);
		outputTableColumnModel.getColumn(2).setMaxWidth(180);
		outputTableColumnModel.getColumn(2).setPreferredWidth(180);
		outputTableColumnModel.getColumn(3).setMaxWidth(180);
		outputTableColumnModel.getColumn(3).setPreferredWidth(180);
//		outputTableColumnModel.getColumn(4).setMaxWidth(100);
//		outputTableColumnModel.getColumn(4).setPreferredWidth(100);
		
//		outputTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		add(new JScrollPane(outputTable), BorderLayout.CENTER);
	}
	
//	private static class ExtendedTableCellEditor extends AbstractCellEditor implements TableCellEditor
//	{
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		
//		JLabel _component = new JLabel();
//		JScrollPane _pane = new JScrollPane(_component, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//
//		/**
//		 * Returns the cell editor component.
//		 *
//		 */
//		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex)
//		{
//			if (value == null) 
//				return null;
//			
//			_component.setText(value != null ? value.toString() : "");
//			_component.setToolTipText(value != null ? value.toString() : "");
//
//			_component.setOpaque(true);
//			_component.setBackground((isSelected) ? Color.BLUE : Color.WHITE);
//			_component.setForeground((isSelected) ? Color.WHITE : Color.BLACK);
//
//			_pane.setHorizontalScrollBar(_pane.createHorizontalScrollBar()); 
//			_pane.setVerticalScrollBar(_pane.createVerticalScrollBar());
//			_pane.setBorder(new EmptyBorder(0,0,0,0));
//			_pane.setToolTipText(value != null ? value.toString() : "");
//			
//			return _pane;
//		}
//		
//		public Object getCellEditorValue()
//		{
//			return _component.getText();
//		}
//	}
	
	public void clear() {
		synchronized (this) {
			outputTableModel.setRowCount(0);
			taskNo = 0;
		}
	}
	
	private int taskNo;

	public TaskView newTask(String resourceName) {
		int taskNoLocal;
		synchronized (this) {
			taskNoLocal = taskNo++;
			outputTableModel.insertRow(0, new Object[]{resourceName, "", new Date().toLocaleString(), "", "", ""});
		}
		
		return new TaskView(taskNoLocal, this);
	}

	public void setTaskStatus(int taskNo, String status) {
		setTaskCell(taskNo, 1, status);
	}

	public void setTaskOutput(int taskNo, Output output) {
		setTaskCell(taskNo, 5, output.toString());
	}

	public void setTaskResult(int taskNo, String result) {
		setTaskCell(taskNo, 4, result);
	}
	
	private void setTaskCell(int taskNo, int column, Object value) {
		int row;
		synchronized (this) {
			row = getRowFromTaskNo(taskNo);
		}
		
		outputTableModel.setValueAt(value, row, column);
	}
	
	private int getRowFromTaskNo(int taskNo) {
		return outputTableModel.getRowCount() - taskNo - 1;
	}

	public void endTask(int taskNo) {
		setTaskCell(taskNo, 3, new Date().toLocaleString());
		setTaskStatus(taskNo, "Finished");
	}
}
