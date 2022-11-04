package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeCommand extends CommandBase{
    Intake intake;
    public IntakeCommand(Intake intake){
        this.intake = intake;
        addRequirements(intake);
    }

    public void runIntake(){
        intake.setPercent(0.35);
    }
}
