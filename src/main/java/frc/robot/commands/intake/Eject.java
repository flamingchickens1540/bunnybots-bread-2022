package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Eject extends CommandBase{
    Intake intake;
    int count;
    int cap;
    double speed;
    public Eject(Intake intake, int cap, double speed){
        this.intake = intake;
        this.cap = cap;
        this.speed = speed;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.setPercent(speed);
        count = 0;
    }

    @Override
    public void execute() {
        count += 1;
    }

    @Override
    public boolean isFinished() {
        return count == cap;
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPercent(0);
    }

}
