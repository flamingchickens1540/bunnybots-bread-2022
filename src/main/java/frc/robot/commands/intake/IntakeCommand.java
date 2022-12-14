package frc.robot.commands.intake;

import java.util.function.DoublePredicate;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeCommand extends CommandBase{
    Intake intake;
    double speed;
    public IntakeCommand(Intake intake, double speed){
        this.intake = intake;
        addRequirements(intake);
        this.speed = speed;
    }
        
    @Override
    public void initialize() {
        intake.setPercent(speed);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPercent(0);
    }
    


}
