package frc.robot.commands.drivetrain;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveAndTurnAprilTag extends CommandBase{
    private Drivetrain drivetrain;
    private PhotonCamera camera;
    private int targetId = -1;
    private PhotonPipelineResult result;
    private boolean keepGoing;
    public DriveAndTurnAprilTag(Drivetrain drivetrain, PhotonCamera camera){
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
        this.camera = camera;
    }

    @Override
    public void initialize() {
        
        List<PhotonTrackedTarget> targets = camera.getLatestResult().getTargets();
        double large = -1;
        for (PhotonTrackedTarget target : targets) {
            if (Math.abs(target.getArea()) > large) {
                large = Math.abs(target.getArea());
                targetId = target.getFiducialId();
            }
        }
        System.out.println(targetId);
    }

    @Override
    public void execute() {
        keepGoing = false;
        List<PhotonTrackedTarget> targets = camera.getLatestResult().getTargets();
        for(PhotonTrackedTarget target: targets){
            System.out.println();
            if(target.getFiducialId() == targetId){
                System.out.println("I DO THINGS");
                double leftPercent = 0;
                double rightPercent = 0;
                double yaw = target.getYaw();
                double area = target.getArea();
                if(Math.abs(yaw) > 3){
                    leftPercent += (yaw/Math.abs(yaw))*0.1;
                    rightPercent += (-yaw/Math.abs(yaw))*0.1;
                }
                if(area < 0.75){
                    leftPercent += -0.1;
                    rightPercent += -0.1;
                }
                drivetrain.setPercent(leftPercent, rightPercent);
                System.out.println(leftPercent + " " + rightPercent);
                if(leftPercent != 0 || rightPercent != 0) keepGoing = true;
                System.out.println(keepGoing);
            }
            break;
        }
    }

    @Override
    public boolean isFinished() {
        return !keepGoing;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setPercent(0, 0);
    }
}
