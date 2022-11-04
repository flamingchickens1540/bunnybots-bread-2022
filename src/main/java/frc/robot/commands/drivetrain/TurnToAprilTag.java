package frc.robot.commands.drivetrain;
import java.util.List;

import com.kauailabs.navx.frc.AHRS;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnToAprilTag extends CommandBase{
    private Drivetrain drivetrain;
    private PhotonCamera camera;
    private int targetId = -1;
    private PhotonPipelineResult result;
    private boolean keepGoing;
    private AHRS navx;
    private double targetAngle;
    private PIDController pidController = new PIDController(0.2, 0, 0);
    public TurnToAprilTag(Drivetrain drivetrain, PhotonCamera camera, AHRS navx){
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
        this.camera = camera;
        this.navx = navx;
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
        result = camera.getLatestResult();
        if(result.hasTargets()){
            List<PhotonTrackedTarget> targets = result.getTargets();
            double large = -1;
            for (PhotonTrackedTarget target : targets) {
                if (Math.abs(target.getArea()) > large) {
                    large = Math.abs(target.getArea());
                    targetId = target.getFiducialId();
                    targetAngle = normalize(target.getYaw() + navx.getYaw());
                }
            }
        }
        System.out.println(navx.getYaw() + " " + targetAngle);
        System.out.println(targetId);
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        // super.execute();
        // result = camera.getLatestResult();
        // List<PhotonTrackedTarget> targets = result.getTargets();
        // keepGoing = false;
        // for(PhotonTrackedTarget target: targets){
        //     if(target.getFiducialId() == targetId){
        //         double yaw = target.getYaw();
        //         // System.out.println(yaw);
        //         if(Math.abs(yaw) < 3) break;
        //         System.out.println(yaw);
        //         drivetrain.setPercent((yaw/Math.abs(yaw))*0.1, (-yaw/Math.abs(yaw))*0.1);
        //         // drivetrain.setPercent(-0.1, -0.1);
        //         keepGoing = true;
        //     }
        // }
        // System.out.println(navx.getYaw());
        double difference = normalize(targetAngle - navx.getYaw());
        // System.out.println(difference);
        // System.out.println(pidController.calculate(difference, navx.getYaw())/10);
        // System.out.println(difference);
        // System.out.println(navx.getYaw());
        System.out.println(navx.getYaw() + " " + targetAngle + " " + difference);
        if(Math.abs(difference) > 3){ 
            // drivetrain.setPercent((difference/Math.abs(difference))*0.1, (-difference/Math.abs(difference))*0.1);
            // drivetrain.setPercent(-pidController.calculate(difference, targetAngle)/10, pidController.calculate(difference, targetAngle)/10);
            drivetrain.setPercent(pidController.calculate(navx.getYaw(), targetAngle)/10, -pidController.calculate(navx.getYaw(), targetAngle)/10);

        }

        
    }
    @Override
    public boolean isFinished() {
        // return !keepGoing;
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        super.end(interrupted);
        drivetrain.setPercent(0, 0);
    }

    public double normalize(double angle){
        if(angle > 180) return angle - 360;
        else if(angle < -180) return angle + 360;
        else return angle;
    }
}
