package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDrive extends CommandBase{
    private XboxController controller;
    private Drivetrain drivetrain;
    public TankDrive(XboxController controller, Drivetrain drivetrain){
        this.controller = controller;
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }    

    @Override
    public void execute() {
        double left = -controller.getLeftY() * 0.5;
        double right = -controller.getRightY() * 0.5;
        double forward = controller.getRightTriggerAxis() * 0.5;
        double backward = -controller.getLeftTriggerAxis() * 0.25;
        left += forward + backward;
        right += forward + backward;
        drivetrain.setPercent(left, right);
    }


}
