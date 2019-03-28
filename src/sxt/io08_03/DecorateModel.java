/**
 * 
 */
package sxt.io08_03;

public class DecorateModel {
	public static void main(String[] args) {
		Mobile mobile = new DecorateModel().new Mobile();
		System.out.println("info:" + mobile.info() + "\n" + "cost: " + mobile.cost());

		MobileShell mobileShell = new DecorateModel().new MobileShell(mobile);
		System.out.println("info:" + mobileShell.info() + "\n" + "cost: " + mobileShell.cost());

	}

	// 1. interface
	interface Mtds {
		String info();

		int cost();
	}

	// 2. mobile
	class Mobile implements Mtds {

		public Mobile() {

		}

		@Override
		public String info() {
			return "an iphone";
		}

		@Override
		public int cost() {
			return 1;
		}
	}

	// 3. abstract_cls
	// default: curPackage_cls; protected: curPackage_cls + child_cls
	abstract class DecorateUnifiedMtds implements Mtds {
		private Mtds mMtds;

		DecorateUnifiedMtds(Mtds mtds) {
			mMtds = mtds;
		}

		public String info() {
			return mMtds.info();
		}

		public int cost() {
			return mMtds.cost();
		}
	}

	// 4. fact_cls
	class MobileShell extends DecorateUnifiedMtds {
		MobileShell(Mtds mtds) {
			super(mtds);
		}

		public String info() {
			return super.info() + " + with a shell";
		}

		public int cost() {
			return super.cost() + 40;
		}
	}
}
