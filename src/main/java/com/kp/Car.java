package com.kp;

public interface Car {
	/**
	 * 移动方法
	 * @param command：直行/转弯
	 */
	void move(CarCommand command);

	/**
	 * 当前x坐标位置
	 * @return
	 */
	int getPositionX();

	/**
	 * 当前y坐标位置
	 * @return
	 */
	int getPositionY();

	/**
	 * 当前转向
	 * @return
	 */
	String getOrientation();
}