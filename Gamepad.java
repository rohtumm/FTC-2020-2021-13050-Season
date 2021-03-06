/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Gamepad", group="Linear Opmode")
//@Disabled
public class Gamepad extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor leftDownDrive;
    DcMotor rightDownDrive;
    DcMotor launcherMotor;
    DcMotor launcherMotor2;
    DcMotor intakeMotor;
    Servo wobbleServo;
    Servo launcherPush;
    double power = 1;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDownDrive  = hardwareMap.get(DcMotor.class, "left_down_drive");
        rightDownDrive = hardwareMap.get(DcMotor.class, "right_down_drive");
        launcherMotor = hardwareMap.get(DcMotor.class, "launcher_motor");
        launcherMotor2 = hardwareMap.get(DcMotor.class, "launcher_motor_2");
        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
        wobbleServo = hardwareMap.get(Servo.class, "wobble_motor");
        launcherPush = hardwareMap.get(Servo.class,"launcher_push");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDownDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDownDrive.setDirection(DcMotor.Direction.REVERSE);
        launcherMotor.setDirection(DcMotor.Direction.FORWARD); //change when making launcher
        launcherMotor2.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD); //change when making intake
        wobbleServo.setDirection(Servo.Direction.FORWARD);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double launcherPower;
            double intakePower;

            launcherPower = 0.85; //different speeds later
            intakePower = .5; //change later
            //launcher
            if(gamepad1.right_trigger > 0.5){
                launcherMotor.setPower(-launcherPower);
                launcherMotor2.setPower(launcherPower);
            } else {
                launcherMotor.setPower(0);
                launcherMotor2.setPower(0);
            }
            //intake
            if(gamepad1.right_bumper){
                launcherPush.setPosition(1.5);
                launcherPush.setPosition(0);
            }
            if(gamepad1.left_trigger > 0.5){
                intakeMotor.setPower(intakePower);
            } else {
                intakeMotor.setPower(0);
            }

            if(gamepad1.left_stick_x>0.75){
                leftDrive.setPower(power);
                rightDrive.setPower(-power);
                leftDownDrive.setPower(power);
                rightDownDrive.setPower(-power);
            } else if (gamepad1.left_stick_x<-0.75){
                leftDrive.setPower(-power);
                rightDrive.setPower(power);
                leftDownDrive.setPower(-power);
                rightDownDrive.setPower(power);
            } else if (gamepad1.right_stick_y>0.75){
                leftDrive.setPower(power);
                rightDrive.setPower(power);
                leftDownDrive.setPower(power);
                rightDownDrive.setPower(power);
            } else if (gamepad1.right_stick_y<-0.75){
                leftDrive.setPower(-power);
                rightDrive.setPower(-power);
                leftDownDrive.setPower(-power);
                rightDownDrive.setPower(-power);
            } else if (gamepad1.right_stick_x>0.75){
                leftDrive.setPower(power);
                rightDrive.setPower(-power);
                leftDownDrive.setPower(-power);
                rightDownDrive.setPower(power);
            } else if (gamepad1.right_stick_x<-0.75){
                leftDrive.setPower(-power);
                rightDrive.setPower(power);
                leftDownDrive.setPower(power);
                rightDownDrive.setPower(-power);
            } else {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
                leftDownDrive.setPower(0);
                rightDownDrive.setPower(0);
            }

            //wobble arm
            if(gamepad1.y){
                wobbleServo.setPosition((int) 0.5);
                wobbleServo.setPosition(0.09);
            } else if (gamepad1.a){
                wobbleServo.setPosition((int) 0);
                wobbleServo.setPosition(-0.09);
            }






            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight

            //commented out by us
            //double drive = -gamepad1.left_stick_y;
            //double turn  =  gamepad1.right_stick_x;
            //leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            //rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.



            // Send calculated power to wheels
            //STARTER CODE COMMENTED OUT BY US
            //shows how to press button and make it move forward
//            if (gamepad1.a) {
//                leftDrive.setPower(0.5);
//                rightDrive.setPower(0.5);
//                leftDownDrive.setPower(0.5);
//                rightDownDrive.setPower(0.5);
//            } else {
//                leftDrive.setPower(0.0);
//                rightDrive.setPower(0.0);
//                leftDownDrive.setPower(0.0);
//                rightDownDrive.setPower(0.0);
//            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", intakePower, launcherPower, power);
            telemetry.update();
        }
    }
}
