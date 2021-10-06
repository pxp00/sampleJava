package sxt.container07_02;

//** HashMap = array[length] + linkedList;
//** reqs -> flow -> sourceCodeBlock

//** NodeMap
class NodeMap<K, V> {
	K mKey;
	V mValue;
	NodeMap<K, V> mNext;

	protected NodeMap(K key, V value) {
		mKey = key;
		mValue = value;
	}
}

class HashMapConsum<K, V> {
	static final int LENGTH_TABLE = 16;
	@SuppressWarnings("unchecked")
	NodeMap<K, V>[] mHashMapTable = (NodeMap<K, V>[]) new NodeMap[LENGTH_TABLE]; // ** bucket array
	int mSize; // cntNodeMap

	// ** use hash get tableIndex
	int tableIndex(K key) {
		return (key.hashCode() & (LENGTH_TABLE - 1)); // ** index = obj_key.hashCode()%length => obj_key.hashCode() & (length -1) //取余
	}

	// ** put
	void put(K key, V value) {
		NodeMap<K, V> nodeMap = new NodeMap<K, V>(key, value);
		boolean isKeyRepeated = false;
		NodeMap<K, V> lastNode = null; // ** save the lastNode use to assigned the endNode

		int index = tableIndex(nodeMap.mKey);
		NodeMap<K, V> tmp = (NodeMap<K, V>) mHashMapTable[index];
		if (null == tmp) {
			mHashMapTable[index] = nodeMap;
			mSize++;
		} else {
			while (null != tmp) {
				if (tmp.mKey.equals(nodeMap.mKey)) {
					tmp.mValue = nodeMap.mValue;
					isKeyRepeated = true;
					break;
				} else {
					lastNode = tmp;
					tmp = tmp.mNext;
				}
			}
			if (!isKeyRepeated) {
				lastNode.mNext = nodeMap;
				mSize++;
			}
		}
	}

	// ** get
	V get(K key) {
		int index = tableIndex(key);
		V value = null;
		NodeMap<K, V> temp = mHashMapTable[index];

		while (temp != null) {
			if (temp.mKey.equals(key)) { // 如果相等，则说明找到了键值对，返回相应的value
				value = temp.mValue;
				break;
			} else {
				temp = temp.mNext;
			}
		}
		return value;
	}

	// ** toString {key0 = value0, key1 = value1, key2 = value2}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		for (NodeMap<K, V> nodeMap : mHashMapTable) {
			NodeMap<K, V> tmp = nodeMap;
			while (null != tmp) {
				sb.append(tmp.mKey + "=" + tmp.mValue + ",");
				tmp = tmp.mNext;
			}
		}
		sb.setCharAt(sb.length() - 1, '}');
		return sb.toString();
	}

	// ** main
	public static void main(String[] args) {
		HashMapConsum<Integer, String> hashMapConsum = new HashMapConsum<>();
		hashMapConsum.put(1, "aa");
		hashMapConsum.put(2, "bb");
		hashMapConsum.put(3, "cc");
		hashMapConsum.put(1, "dd");
		System.out.println(hashMapConsum);

		System.out.println(hashMapConsum.get(2));
		System.out.println(hashMapConsum.get(1));
		System.out.println(hashMapConsum.get(3));

	}
}
