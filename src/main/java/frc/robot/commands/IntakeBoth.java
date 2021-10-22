package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Box;
import frc.robot.subsystems.Intake;


public class IntakeBoth extends CommandBase {

    boolean sensorStop;
    boolean stopTimerSet;

    public IntakeBoth(boolean sensorStop) {
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(Box.getInstance(), Intake.getInstance());
    }

    @Override
    public void initialize() {
        stopTimerSet = false;
    }

    @Override
    public void execute() {
        RobotContainer.box.set(.65);
        RobotContainer.intake.setWheels(.5);
    }

    @Override
    public boolean isFinished() {
        if(stopTimerSet) {
            // (new DriverRaiseGroup()).start();
            return true;
          }
          else if(sensorStop && RobotContainer.box.hasBall() && !stopTimerSet) {
            withTimeout(0.5);
            stopTimerSet = true;
            return false;
          }
          else return false;
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.box.set(0);
        RobotContainer.intake.setWheels(0);
    }
}
