package application;

public class Snippet {
	private TreeTableView buildFileBrowserTreeTableView() {
	    TreeItem<File> root = createNode(new File("/"));
	    root.setExpanded(true);
	 
	    final TreeTableView<File> treeTableView = new TreeTableView<File>();
	    treeTableView.setShowRoot(true);
	    treeTableView.setRoot(root);
	 
	    // --- name column
	    TreeTableColumn<File, String> nameColumn = new TreeTableColumn<File, String>("Name");
	    nameColumn.setPrefWidth(300);
	    nameColumn.setCellValueFactory(new Callback<CellDataFeatures<File, String>, ObservableValue<String>>() {
	        @Override public ObservableValue<String> call(CellDataFeatures<File, String> p) {
	            File f = p.getValue().getValue();
	            String text = f.getParentFile() == null ? "/" : f.getName();
	            return new ReadOnlyObjectWrapper<String>(text);
	        }
	    });
	 
	    // --- size column
	    TreeTableColumn<File, File> sizeColumn = new TreeTableColumn<File, File>("Size");
	    sizeColumn.setPrefWidth(100);
	    sizeColumn.setCellValueFactory(new Callback<CellDataFeatures<File, File>, ObservableValue<File>>() {
	        @Override public ObservableValue<File> call(CellDataFeatures<File, File> p) {
	            return new ReadOnlyObjectWrapper<File>(p.getValue().getValue());
	        }
	    });
	    sizeColumn.setCellFactory(new Callback<TreeTableColumn<File, File>, TreeTableCell<File, File>>() {
	        @Override public TreeTableCell<File, File> call(final TreeTableColumn<File, File> p) {
	            return new TreeTableCell<File, File>() {
	                @Override protected void updateItem(File item, boolean empty) {
	                    super.updateItem(item, empty);
	 
	                    TreeTableView treeTable = p.getTreeTableView();
	 
	                    // if the File is a directory, it has no size...
	                    if (getIndex() >= treeTable.impl_getTreeItemCount()) {
	                        setText(null);
	                    } else {
	                        TreeItem<File> treeItem = treeTable.getTreeItem(getIndex());
	                        if (item == null || empty || treeItem == null ||
	                                treeItem.getValue() == null || treeItem.getValue().isDirectory()) {
	                            setText(null);
	                        } else {
	                            setText(nf.format(item.length()) + " KB");
	                        }
	                    }
	                }
	            };
	        }
	    });
	    sizeColumn.setComparator(new Comparator<File>() {
	        @Override public int compare(File f1, File f2) {
	            long s1 = f1.isDirectory() ? 0 : f1.length();
	            long s2 = f2.isDirectory() ? 0 : f2.length();
	            long result = s1 - s2;
	            if (result < 0) {
	                return -1;
	            } else if (result == 0) {
	                return 0;
	            } else {
	                return 1;
	            }
	        }
	    });
	 
	    // --- modified column
	    TreeTableColumn<File, Date> lastModifiedColumn = new TreeTableColumn<File, Date>("Last Modified");
	    lastModifiedColumn.setPrefWidth(130);
	    lastModifiedColumn.setCellValueFactory(new Callback<CellDataFeatures<File, Date>, ObservableValue<Date>>() {
	        @Override public ObservableValue<Date> call(CellDataFeatures<File, Date> p) {
	            return new ReadOnlyObjectWrapper<Date>(new Date(p.getValue().getValue().lastModified()));
	        }
	    });
	    lastModifiedColumn.setCellFactory(new Callback<TreeTableColumn<File, Date>, TreeTableCell<File, Date>>() {
	        @Override public TreeTableCell<File, Date> call(TreeTableColumn<File, Date> p) {
	            return new TreeTableCell<File, Date>() {
	                @Override protected void updateItem(Date item, boolean empty) {
	                    super.updateItem(item, empty);
	 
	                    if (item == null || empty) {
	                        setText(null);
	                    } else {
	                        setText(df.format(item));
	                    }
	                }
	            };
	        }
	    });
	 
	    treeTableView.getColumns().setAll(nameColumn, sizeColumn, lastModifiedColumn);
	 
	    return treeTableView;
	}
	 
	private TreeItem<File> createNode(final File f) {
	    final TreeItem<File> node = new TreeItem<File>(f) {
	        private boolean isLeaf;
	        private boolean isFirstTimeChildren = true;
	        private boolean isFirstTimeLeaf = true;
	 
	        @Override public ObservableList<TreeItem<File>> getChildren() {
	            if (isFirstTimeChildren) {
	                isFirstTimeChildren = false;
	                super.getChildren().setAll(buildChildren(this));
	            }
	            return super.getChildren();
	        }
	 
	        @Override public boolean isLeaf() {
	            if (isFirstTimeLeaf) {
	                isFirstTimeLeaf = false;
	                File f = (File) getValue();
	                isLeaf = f.isFile();
	            }
	 
	            return isLeaf;
	        }
	    };
	    return node;
	}
	 
	private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
	    File f = (File) TreeItem.getValue();
	    if (f != null && f.isDirectory()) {
	        File[] files = f.listFiles();
	        if (files != null) {
	            ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();
	 
	            for (File childFile : files) {
	                children.add(createNode(childFile));
	            }
	 
	            return children;
	        }
	    }
	 
	    return FXCollections.emptyObservableList();
	}
}

