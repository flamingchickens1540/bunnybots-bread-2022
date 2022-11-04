package frc.robot.commands.drivetrain;
import java.lang.annotation.Target;
import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Main;

public class DriveToAprilTag extends CommandBase{
    private Drivetrain drivetrain;
    private PhotonCamera camera;
    private int targetId = -1;
    private PhotonPipelineResult result;
    boolean keepGoing;
    public DriveToAprilTag(Drivetrain drivetrain, PhotonCamera camera){
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
        this.camera = camera;
    }

    @Override
    public void initialize() {
        result = camera.getLatestResult();
        if(result.hasTargets()){
            List<PhotonTrackedTarget> targets = result.getTargets();
            double middle = Integer.MAX_VALUE;
            for (PhotonTrackedTarget target : targets) {
                if (Math.abs(target.getYaw()) < middle) {
                    middle = Math.abs(target.getYaw());
                    targetId = target.getFiducialId();
                    if(target.getArea() > 0.75){
                        System.out.println("Canceling");
                        // end(f/);
                        // cancel();
                    }
                }
            }
            if(targetId != -1){
                drivetrain.setPercent(-0.1, -0.1);
            }
        }
    }

    @Override
    public void execute() {
        super.execute();
        keepGoing = false;
        List<PhotonTrackedTarget> targets = camera.getLatestResult().getTargets();
        
        for(PhotonTrackedTarget target: targets){
            if(target.getFiducialId() == targetId){
                if(target.getArea() < 0.75){
                    keepGoing = true;
                }
                break;
            }
        }
        
    }
    @Override
    public boolean isFinished() {
        // return false;
        return !keepGoing;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        drivetrain.setPercent(0, 0);
    }
}
