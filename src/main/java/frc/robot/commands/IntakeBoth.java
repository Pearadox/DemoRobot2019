package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
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

    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
