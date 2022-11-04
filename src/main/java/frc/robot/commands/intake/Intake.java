package frc.robot.commands.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    VictorSPX victor = new VictorSPX(Constants.IntakeConstants.VICTOR);
    public Intake(){

    }

    public void setPercent(double percent){
        victor.set(ControlMode.PercentOutput, percent);
    }

}
