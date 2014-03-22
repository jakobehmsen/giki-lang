package giki.gui;

import giki.parser.ResourceStore;

public class ResourceStoreUsage {
	private String resourceName;
	private ResourceStore resourceStore;
	
	public ResourceStoreUsage(String resourceName, ResourceStore resourceStore) {
		this.resourceName = resourceName;
		this.resourceStore = resourceStore;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	public ResourceStore getResourceStore() {
		return resourceStore;
	}
}
