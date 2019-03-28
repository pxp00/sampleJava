package sxt.container07_01;

// array.length -> container length (C: sizeof(arrName))
// arrayList.size -> ctx size(arrayList have not fixed length)
public class CustomArray<E> {
	private static final int DEF_CAPACITY = 10;
	Object[] mElementData; // container
	int mSize; // ctx size

	protected CustomArray(int size) {
		mElementData = new Object[size];
	}

	protected CustomArray() {
		mElementData = new Object[DEF_CAPACITY];
	}

	void add(E element) {
		// when do you extend capacity?
		if (mSize >= mElementData.length) {
			Object[] newArray = new Object[mElementData.length + (mElementData.length >> 1)]; // newArray
			System.arraycopy(mElementData, 0, newArray, 0, mElementData.length); // copy data
			mElementData = newArray; // ref_oldArray = ref_newArray
		}
		mElementData[mSize++] = element;
	}

	void remove(E element) {
		for (int index = 0; index < mSize; index++) {
			if (element.equals(mElementData[index])) {
				remove(index);
			}
		}
	}

	void remove(int index) {
		rangeCheck(index);

		int moveCnt = mElementData.length - (index + 1);
		System.arraycopy(mElementData, index + 1, mElementData, index, moveCnt); // copy data

		mElementData[--mSize] = null; // padding the last pos
	}

	@SuppressWarnings("unchecked") // **
	E get(int index) {
		rangeCheck(index);
		return (E) mElementData[index]; // ** unchecked cast
	}

	void set(int index, E element) {
		rangeCheck(index);
		mElementData[index] = element;
	}

	// 0<= index <=size -1
	void rangeCheck(int index) {
		if (index < 0 || index > (mSize - 1)) {
			throw new RuntimeException("index illegal"); // Runtime_exception handle by sys default
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("["); // strcat ignore '\0'
		for (int i = 0; i < mSize; i++) {
			sb.append(mElementData[i]);
			sb.append(",");
		}
		sb.setCharAt(sb.length() - 1, ']'); // **

		return sb.toString();
	}

	public static void main(String[] args) {
		// new a container
		CustomArray<String> customArray = new CustomArray<>(4);
		// add
		customArray.add("aa");
		customArray.add("bb");
		customArray.add("cc");
		customArray.add("dd");
		customArray.add("ee");

		// print container
		System.out.println(customArray);

		System.out.println(customArray.get(1));
		customArray.set(2, "aa");
		System.out.println(customArray);

		customArray.remove(0);
		System.out.println(customArray);

		customArray.remove("dd");
		System.out.println(customArray);

	}
}
