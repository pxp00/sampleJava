package sxt.serverlet11_02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
	private List<Entity> entitys = null;
	private List<Mapping> mappings = null;

	// key-->servlet-name value -->servlet-class
	private Map<String, String> entityMap = new HashMap<String, String>();
	// key -->url-pattern value -->servlet-name
	private Map<String, String> mappingMap = new HashMap<String, String>();

	public WebContext(List<Entity> entitys, List<Mapping> mappings) {
		this.entitys = entitys;
		this.mappings = mappings;

		// list of jBean convert to Map
		for (Entity entity : entitys) {
			entityMap.put(entity.getName(), entity.getClz());
		}
		// list of jBean convert to Map
		for (Mapping mapping : mappings) {
			for (String pattern : mapping.getPatterns()) {
				mappingMap.put(pattern, mapping.getName());
			}
		}
	}

	/**
	 * 通过URL的路径找到了对应class
	 * 
	 * @param pattern
	 * @return
	 */
	public String getClz(String pattern) {
		String name = mappingMap.get(pattern);
		return entityMap.get(name);
	}

}
