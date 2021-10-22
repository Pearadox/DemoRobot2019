// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IMU extends SubsystemBase {
  /** Creates a new IMU. */
  private static final IMU INSTANCE = new IMU();

  @SuppressWarnings("WeakerAccess")
    public static IMU getInstance() {
        return INSTANCE;
    }

  public static AHRS navx;
	double yawOffset = 0, pitchOffset = 0;
	
	public IMU () {
		navx = new AHRS(SPI.Port.kMXP);
		navx.zeroYaw();
  }
  
	public double getYaw() {
		return navx.getAngle() - yawOffset;
	}

	public double getPitch() {
		return navx.getRoll() - pitchOffset;
	}
	
	public void zero() {
		zero(0);
	}

	public void zero(double extraOffset) {
		yawOffset += getYaw() - extraOffset;
		pitchOffset += getPitch();
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
