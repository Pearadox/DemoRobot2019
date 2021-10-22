package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;

public class Drivetrain implements Subsystem {

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.

    /**
     * The Singleton instance of this Drivetrain. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    private final static Drivetrain INSTANCE = new Drivetrain();

    /**
     * Returns the Singleton instance of this Drivetrain. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code Drivetrain.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static Drivetrain getInstance() {
        return INSTANCE;
    }

    VictorSPX leftSlave1 = new VictorSPX(RobotMap.CANLeftSlave1Victor);
    VictorSPX leftSlave2 = new VictorSPX(RobotMap.CANLeftSlave2Victor);
    VictorSPX rightSlave1 = new VictorSPX(RobotMap.CANRightSlave1Victor);
    VictorSPX rightSlave2 = new VictorSPX(RobotMap.CANRightSlave2Victor);
    TalonSRX leftMaster = new TalonSRX(RobotMap.CANLeftMasterTalon);
    TalonSRX rightMaster = new TalonSRX(RobotMap.CANRightMasterTalon);


    /**
     * Creates a new instance of this Drivetrain. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    private Drivetrain() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        rightSlave1.follow(rightMaster);
        rightSlave2.follow(rightMaster);
        leftSlave1.follow(leftMaster);
        leftSlave1.follow(leftMaster);

        rightMaster.setInverted(true);
    }

    public void arcadeDrive(double throttle, double twist, double factor) {
        var scaledThrottleSquared = factor * throttle * throttle;
        var scaledTwistSquared = factor * twist * twist;
        drive(-scaledThrottleSquared + scaledTwistSquared, -scaledThrottleSquared - scaledTwistSquared);
    }

    private void drive(double leftSpeed, double rightSpeed) {
        leftMaster.set(ControlMode.PercentOutput, leftSpeed);
        rightMaster.set(ControlMode.PercentOutput, rightSpeed);
    }
}

