package frc.robot.commands.drivetrain;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.utils.ChickenPhotonCamera;

public class TurnToAprilTag extends CommandBase{
    Drivetrain drivetrain;
    ChickenPhotonCamera camera;
    PhotonTrackedTarget target;
    double targetAngle = 0;

    TurnToAprilTag(Drivetrain drivetrain, ChickenPhotonCamera camera){
        this.drivetrain = drivetrain;
        this.camera = camera;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        target = camera.getTarget(18);
        drivetrain.zeroHeading();
        if(target != null){
            targetAngle = target.getYaw();
        }
    }

    @Override
    public void execute() {
        drivetrain.setPercent((targetAngle - drivetrain.getYaw())/30 * 0.3, (targetAngle - drivetrain.getYaw())/-30 * 0.3);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(targetAngle - drivetrain.getYaw()) < 2; 
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setPercent(0, 0);
    }

}
