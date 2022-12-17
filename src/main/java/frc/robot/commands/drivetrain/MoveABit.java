package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class MoveABit extends CommandBase{
    Drivetrain drivetrain;
    int count;
    double speed;
    int cap;
    public MoveABit(Drivetrain drivetrain, int cap, double speed){
        this.drivetrain = drivetrain;
        this.cap = cap;
        this.speed = speed;
        addRequirements(drivetrain);
    }
    @Override
    public void initialize() {
        drivetrain.setPercent(speed, speed);
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
        drivetrain.setPercent(0, 0);
    }
}
