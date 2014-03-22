package giki.gui;

import giki.parser.ResourceStore;

import java.util.List;

public interface ResourceStoreProvider {
	public interface Listener {
		void selectedResourceStoreChanged(ResourceStoreProvider provider);
		void popularResourcesChanged(ResourceStoreProvider provider);
	}
	
	ResourceStore getSelectedResourceStore();
	void selectResourceStore(ResourceStore resourceStore);
	void addListener(Listener listener);
	void removeListener(Listener listener);
	List<ResourceStore> getPopularResourceStores();
}
