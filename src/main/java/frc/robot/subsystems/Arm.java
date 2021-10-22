// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

/** Add your docs here. */
public class Arm implements Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  double angleMin = 40.;
  CANSparkMax armMotor;
  CANEncoder encoder;
  DigitalInput limit;

  private final static Arm INSTANCE = new Arm();

  @SuppressWarnings("WeakerAccess")
    public static Arm getInstance() {
      return INSTANCE;
  }

  private Arm(){
    armMotor = new CANSparkMax(RobotMap.CANArmBLDCSparkMax, MotorType.kBrushless);
    encoder = armMotor.getEncoder();
    limit = new DigitalInput(RobotMap.limitSwitchArm);
    armMotor.setIdleMode(IdleMode.kBrake);
    armMotor.setInverted(false);
  }

  public double getAngle(){
    return -getRawEncoder()*3.60 + angleMin;
  }

  public double getRawEncoder() {
    return -encoder.getPosition();
  }

  public void set(double percentOutput){
    // if(getAngle() > 170 && percentOutput > 0) return;
    // if(getLimit() && percentOutput < 0) return;
    armMotor.set(percentOutput);
  }

  public void setRawSpeed(double speed){
    armMotor.set(speed);
  }

  public void zero() {
    encoder.setPosition(0);
  }

  public boolean getLimit() {
    return !limit.get();
  }

  public double calculateHoldOutput(double angle){
    angle += RobotContainer.gyro.getPitch();
    double amplitude = 0.025;
    double equation = amplitude * Math.sin(angle*Math.PI/180);
    return equation;
  }
}
