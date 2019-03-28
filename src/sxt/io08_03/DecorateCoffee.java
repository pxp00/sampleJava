package sxt.io08_03;

//模拟咖啡 
//1、抽象组件:需要装饰的抽象对象(接口或抽象父类) 
//2、具体组件:需要装饰的对象 
//3、抽象装饰类:包含了对抽象组件的引用以及装饰着共有的方法 
//4、具体装饰类:被装饰的对象

// ** ModelDecorate: enhance mtd (coffee + milk + sugar)
// ** ModelProxy: add mtd (wedding company)

// output(flow):
// 1. interface;
// 2. orig_cls implement interface;
// 3. abstact_cls implements interface; (uni)
// 4. decorate_cls extends abstract_cls;

// main: obj_orig(mtd1, mtd2), obj_decorate(obj_orig)(enhance mtd1, mtd2)
public class DecorateCoffee {
	public static void main(String[] args) {

		// original
		Drink coffee = new Coffee();
		System.out.println(coffee.info() + "-->" + coffee.cost());

		// decorate1
		Drink suger = new Suger(coffee); // 装饰
		System.out.println(suger.info() + "-->" + suger.cost());

		// decorate2
		Drink milk = new Milk(coffee);// 装饰
		System.out.println(milk.info() + "-->" + milk.cost());

		// decorate3
		milk = new Milk(suger);// 装饰
		System.out.println(milk.info() + "-->" + milk.cost());
	}
}

// 1. 抽象组件: interface
interface Drink {
	double cost(); // 费用

	String info(); // 说明
}

// 2. 具体组件: coffee
class Coffee implements Drink {
	private String name = "原味咖啡";

	@Override
	public double cost() {
		return 10;
	}

	@Override
	public String info() {
		return name;
	}
}

// 3. 抽象装饰类: abstract_cls (unified entrance)
abstract class Decorate implements Drink {
	// 对抽象组件的引用
	private Drink drink;

	public Decorate(Drink drink) { // ** init: delivery obj_orig
		this.drink = drink;
	}

	@Override
	public double cost() {
		return drink.cost(); // ** invoke org_mtd
	}

	@Override
	public String info() {
		return drink.info(); // ** invoke org_mtd
	}
}

// 4. 具体装饰类: milk: fact_cls
class Milk extends Decorate { // ** extends abstact_cls

	public Milk(Drink drink) {
		super(drink); // **initial
	}

	@Override
	public double cost() {
		return super.cost() * 4; // ** enhance mtd
	}

	@Override
	public String info() {
		return super.info() + "加入了牛奶"; // ** enhance mtd
	}
}

// 4. 具体装饰类: sugar
class Suger extends Decorate {

	public Suger(Drink drink) {
		super(drink);
	}

	@Override
	public double cost() {
		return super.cost() * 2;
	}

	@Override
	public String info() {
		return super.info() + "加入了蔗糖";
	}
}
