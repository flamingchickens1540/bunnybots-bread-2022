package frc.robot.commands.drivetrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDrive extends CommandBase{
    private XboxController controller;
    private Drivetrain drivetrain;
    private double deadzone = 0.1;
    public ArcadeDrive(XboxController controller, Drivetrain drivetrain){
        this.controller = controller;
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    public void setMotors(){
        double throttle = Math.abs(controller.getLeftY()) > deadzone ? -0.5*controller.getLeftY() : 0;
        double turn = Math.abs(controller.getRightX()) > deadzone ? -0.5*controller.getRightX() : 0;
        drivetrain.setPercent(-turn + throttle,(turn + throttle));
    }

    @Override
    public void execute() {
        super.execute();
        setMotors();
    }
}
