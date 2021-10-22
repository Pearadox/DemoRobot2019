package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Box;
import frc.robot.subsystems.Intake;
import frc.robot.RobotContainer;


public class OuttakeBoth extends CommandBase {

    public OuttakeBoth() {
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(Intake.getInstance(), Box.getInstance());
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        RobotContainer.box.set(-1);
        RobotContainer.intake.setWheels(-1);
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.box.set(0);
        RobotContainer.intake.setWheels(0);
    }
}
