package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Intake implements Subsystem {

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.

    /**
     * The Singleton instance of this Intake. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    private final static Intake INSTANCE = new Intake();

    /**
     * Returns the Singleton instance of this Intake. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code Intake.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static Intake getInstance() {
        return INSTANCE;
    }

    private VictorSPX intakeWheels = new VictorSPX(RobotMap.CANIntakeVictor);
    private Solenoid raiserSol1 = new Solenoid(RobotMap.IntakeSolenoid1);
    private Solenoid raiserSol2 = new Solenoid(RobotMap.IntakeSolenoid1);

    public void lower(){
        raiserSol1.set(true);
        raiserSol1.set(true);
    }

    public void raise(){
        raiserSol1.set(false);
        raiserSol2.set(false);
    }

    public boolean isLow() {
        return raiserSol1.get();
    }

    public void setWheels(double setSpeed) {
        intakeWheels.set(ControlMode.PercentOutput, setSpeed);
    }

    public void toggleSolenoids() {
        if (isLow()) { raise(); }
        else { lower(); }
    }

    public Command intakeToggleCommand() {
        return new InstantCommand(
                this::toggleSolenoids, this
        );
    };

    /**
     * Creates a new instance of this Intake. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    private Intake() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
    }
}

