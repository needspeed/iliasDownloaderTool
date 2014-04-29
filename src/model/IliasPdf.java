package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class IliasPdf extends IliasTreeNode {
	private static final long serialVersionUID = -2841996600829969452L;
	private final int size;
	private BooleanProperty isMarkedAsRead; 

	public IliasPdf(String name, String url, IliasFolder parentDirectory, int size) {
		super(name, url, parentDirectory);
		this.size = size;
		initializeProperties();
	}

	public int getSize() {
		return size;
	}

	public boolean isIgnored() {
		return Settings.getInstance().isIgnored(createStoreKey()) != -1;
	}

	public void setIgnored(boolean b) {
		if (b) {
			Settings.getInstance().storeIgnoredPdfSize(createStoreKey(), getSize());
		} else {
			Settings.getInstance().removeIgnoredPdfSize(createStoreKey());
		}
	}

	public void setRead(boolean b) {

	}
	
	public BooleanProperty isMarkedAsReadProperty() {
		initializeProperties();
		return isMarkedAsRead; 
	}


	private String createStoreKey() {
		String key = getUrl();
		final int beginIndex = key.indexOf("ref_id=");
		final int endIndex = key.indexOf("&cmd=sendfile");
		key = key.substring(beginIndex + 7, endIndex);
		return key;
	}

	private void initializeProperties() {
		if (isMarkedAsRead == null) {
			isMarkedAsRead = new SimpleBooleanProperty(false); 
		}
	}
}
