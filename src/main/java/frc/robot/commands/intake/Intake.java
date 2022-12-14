package frc.robot.commands.intake;

import com.revrobotics.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase{
    CANSparkMax spark = new CANSparkMax(IntakeConstants.SPARK, CANSparkMaxLowLevel.MotorType.kBrushless);
    public Intake(){
        spark.setInverted(true);
    }

    public void setPercent(double percent){
        spark.set(percent);
    }

}
