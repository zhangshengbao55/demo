package com.kp;

import java.util.Scanner;

public class AutoCar implements Car {
	private Integer x;//所处车库在x轴位置
	private Integer y;//所处车库在y轴位置
	private Orientation orientation;//车的行驶方向
	private Park curPark;//车库大小

	public AutoCar(Integer x, Integer y, Orientation orientation, Park curPark) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
		this.curPark = curPark;
	}

	/**
	 * 行驶方法
	 * @param command：直行/转弯
	 */
	@Override
	public void move(CarCommand command) {
		switch (command) {
			case TURN:
				this.orientation = Orientation.nextOrientation(orientation);
				break;
			case FORWARD:
				forwardCar();
				break;
		}
	}

	/**
	 * 直行
	 */
	private void forwardCar() {
		switch (orientation) {
			case EAST:
				if (++this.x > curPark.getL())
					throw new AgainstWallException();
				break;
			case SOUTH:
				if (++this.y > curPark.getW())
					throw new AgainstWallException();
				break;
			case WEST:
				if (--this.x < 0)
					throw new AgainstWallException();
				break;
			case NORTH:
				if (--this.y < 0)
					throw new AgainstWallException();
				break;
		}
	}

	/**
	 * 顺时针转向
	 */
	private enum  Orientation {
		EAST,SOUTH,WEST,NORTH;

		public static Orientation nextOrientation(Orientation cur) {
			if (cur == null)
				throw new OrientationNotFoundException();
			if (EAST.equals(cur)) {
				return SOUTH;
			} else if (SOUTH.equals(cur)) {
				return WEST;
			} else if (WEST.equals(cur)) {
				return NORTH;
			} else {
				return EAST;
			}
		}
	}

	@Override
	public int getPositionX() {
		return x;
	}

	@Override
	public int getPositionY() {
		return y;
	}

	@Override
	public String getOrientation() {
		return this.orientation.toString();
	}




	public static void main(String[] args) {
		AutoCar autoCar = new AutoCar(1, 1, Orientation.NORTH, new Park(5, 5));
		Scanner scanner = new Scanner(System.in);

		while (true){
			int ss = scanner.nextInt();
			autoCar.move(CarCommand.FORWARD);
			System.out.println(""+autoCar.getOrientation()+autoCar.getPositionX()+autoCar.getPositionY());
		}
	}
}
