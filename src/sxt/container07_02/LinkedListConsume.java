package sxt.container07_02;

class NodeT {
	NodeT mPrev;
	NodeT mNext;
	Object mData;

	protected NodeT(Object data) {
		mData = data;
	}
}

class LinkedLishT<E> {
	NodeT mHead;
	NodeT mRear;
	int mSize;

	// ** get index = 2: tmp = head, tmp = tmp.next
	public E get(int index) {

		return (E) getNode(index).mData;
	}

	public NodeT getNode(int index) {
		if (index < 0 || index > (mSize - 1)) { // ** index: [0, size - 1]
			throw new RuntimeException("index illegal: " + index);
		}
		NodeT tmp = null;

		if (index < 0 || index > (mSize - 1)) { // ** index: [0, size - 1]
			throw new RuntimeException("index illegal: " + index);
		}

		if (index <= (mSize >> 1)) {
			tmp = mHead;
			for (int i = 0; i < index; i++) { // times_loop: (index - 0)
				tmp = tmp.mNext;
			}
		} else {
			tmp = mRear;
			for (int i = mSize - 1; i > index; i--) { // times_loop (size -1) - index
				tmp = tmp.mPrev;
			}
		}
		return tmp;
	}

	public void insert(int index, E obj) {
		NodeT newNode = new NodeT(obj);
		NodeT tmp = getNode(index);
		if (null != tmp) {
			NodeT up = tmp.mPrev;
			if (null != up) {
				up.mNext = newNode;
				newNode.mPrev = up;

				tmp.mPrev = newNode;
				newNode.mNext = tmp;
			} else {
				newNode.mPrev = null;
				newNode.mNext = tmp;

				tmp.mPrev = newNode;
			}

			if (0 == index) {
				mHead = newNode;
			}
		}
	}

	// node_up, node_x, node_down
	public void remove(int index) {
		NodeT tmp = getNode(index);
		if (null != tmp) {
			NodeT up = tmp.mPrev;
			NodeT down = tmp.mNext;

			if (null != up) {
				up.mNext = down;
			}

			if (null != down) {
				down.mPrev = up;
			}

			if (0 == index) {
				mHead = down;
			}
			if ((mSize - 1) == index) {
				mRear = up;
			}
		}
		mSize--;
	}

	// ** add0
	void add(NodeT node) {
		if (mHead == null && mRear == null) {
			mHead = node;
			mRear = node;
		} else {
			node.mPrev = mRear;
			node.mNext = null;

			mRear.mNext = node;
			mRear = node;

		}
		mSize++;
	}

	// ** add1
	void add(E data) {
		NodeT node = new NodeT(data);
		if (mHead == null && mRear == null) {
			mHead = node;
			mRear = node;
		} else {
			node.mPrev = mRear;
			node.mNext = null;

			mRear.mNext = node;
			mRear = node;
		}
		mSize++;
	}

	public String toString() {
		NodeT node = mHead;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		while (node != null) {
			sb.append(node.mData);
			sb.append(",");
			node = node.mNext;
		}
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}
}

public class LinkedListConsume {
	public static void main(String[] args) {
		LinkedLishT<String> list = new LinkedLishT<>();
		list.add(new NodeT("aa"));
		list.add(new NodeT("bb"));
		list.add(new NodeT(12));
		list.add("cc");
		System.out.println(list);

		System.out.println(list.get(3));
		// System.out.println(list.get(2)); //** ClassCastException

		list.remove(3);
		System.out.println(list.get(1));

		list.insert(0, "hugo");
		System.out.println(list);

		list.insert(list.mSize - 1, "lisa");
		System.out.println(list);

	}
}
