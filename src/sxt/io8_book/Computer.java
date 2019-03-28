/**
 * 
 */
package sxt.io8_book;

class Cpu {
	public void run() {
		System.out.println("quickly.........");
	}
}

class MainBoard {
	public void connect() {
		System.out.println("connect...........");
	}
}

class Memory {
	public void store() {
		System.out.println("store........");
	}
}

public class Computer {
	Cpu cpu;
	Memory memory;
	MainBoard mainBoard;

	public void work() {
		cpu.run();
		memory.store();
		mainBoard.connect();
	}

	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.cpu = new Cpu();
		computer.mainBoard = new MainBoard();
		computer.memory = new Memory();
		computer.work();
	}
}