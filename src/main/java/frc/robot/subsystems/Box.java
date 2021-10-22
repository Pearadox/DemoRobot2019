package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;

public class Box implements Subsystem {

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.

    /**
     * The Singleton instance of this Box. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    private final static Box INSTANCE = new Box();

    /**
     * Returns the Singleton instance of this Box. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code Box.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static Box getInstance() {
        return INSTANCE;
    }

    VictorSPX boxIntake = new VictorSPX(RobotMap.CANBoxIntakeVictor);
    Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ultrasonicTrig, RobotMap.ultrasonicEcho);

    public void set(double percentOutput){
        boxIntake.set(ControlMode.PercentOutput, percentOutput);
    }

    public void setHold() {
        set(0.1);
    }

    public double getUltrasonic() {
        double reading = ultrasonic.getRangeInches();
        if(reading > 300) reading = 0;
        return reading;
    }

    public boolean hasBall() {
        return getUltrasonic() < 2;
    }

    /**
     * Creates a new instance of this Box. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    private Box() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        ultrasonic.setAutomaticMode(true);
        boxIntake.setInverted(true);
        boxIntake.setNeutralMode(NeutralMode.Brake);

        setDefaultCommand(new RunCommand(this::setHold, this));
    }
}

