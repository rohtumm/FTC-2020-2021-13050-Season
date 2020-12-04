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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Forward", group="Linear Opmode")
//@Disabled
public class Forward extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor leftDownDrive;
    DcMotor rightDownDrive;
    Servo launcherPush;
    double power = 1;
    char targetZone = 'A';


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        launcherPush = hardwareMap.get(Servo.class, "launcher_push");
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDownDrive  = hardwareMap.get(DcMotor.class, "left_down_drive");
        rightDownDrive = hardwareMap.get(DcMotor.class, "right_down_drive");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDownDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDownDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        // Sample Code (shows how to move the robot forward for 2 seconds
        waitForStart();
        runtime.reset();


        //wobble target zones

        leftDrive.setPower(-power); // A and C start with going left thats why i have this out of the if statements
        rightDrive.setPower(-power);
        leftDownDrive.setPower(power);
        rightDownDrive.setPower(power);
        if (targetZone=='A'){
            sleep(184);//move left for 367 milliseconds
            leftDrive.setPower(power); //changes the power to make it move forward
            rightDrive.setPower(power);
            sleep(841); //moves forward for 841 milliseconds
        } else if (targetZone=='B'){
            leftDrive.setPower(power); //changes the power to make it move forward
            rightDrive.setPower(power);
            sleep(1208); //moves forward for 1208 milliseconds
            leftDownDrive.setPower(-power); // changes power to make it move right
            rightDownDrive.setPower(-power);
            sleep(184); //moves right for 184 (the moving left and right is to avoid the rings in the center)
        } else if (targetZone=='C'){
            sleep(184); // moves left for 367 milliseconds
            leftDrive.setPower(power); //changes power to move forward
            rightDrive.setPower(power);
            sleep(1575); //moves forward for 1575 milliseconds
        }
        leftDrive.setPower(0); //outside because they all stop at the end
        rightDrive.setPower(0);
        leftDownDrive.setPower(0);
        rightDownDrive.setPower(0);

        //end of wobble target zone moving


//        leftDrive.setPower(power);
//        rightDrive.setPower(power);
//        leftDownDrive.setPower(power);
//        rightDownDrive.setPower(power);
//        sleep(2000);
//
//        power = 0.0;
//
//        leftDrive.setPower(power);
//        rightDrive.setPower(power);
//        leftDownDrive.setPower(power);
//        rightDownDrive.setPower(power);
        //End Of Sample Code

        // run until the end of the match (driver presses STOP)
//        while (opModeIsActive()) {
//
//            // Setup a variable for each drive wheel to save power level for telemetry
//            double leftPower;
//            double rightPower;
//
//            // Choose to drive using either Tank Mode, or POV Mode
//            // Comment out the method that's not used.  The default below is POV.
//
//            // POV Mode uses left stick to go forward, and right stick to turn.
//            // - This uses basic math to combine motions and is easier to drive straight.
//            double drive = -gamepad1.left_stick_y;
//            double turn  =  gamepad1.right_stick_x;
//            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
//            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
//
//            // Tank Mode uses one stick to control each wheel.
//            // - This requires no math, but it is hard to drive forward slowly and keep straight.
//            // leftPower  = -gamepad1.left_stick_y ;
//            // rightPower = -gamepad1.right_stick_y ;
//
//            // Send calculated power to wheels
//            leftDrive.setPower(leftPower);
//            rightDrive.setPower(rightPower);
//
//            // Show the elapsed game time and wheel power.
//            telemetry.addData("Status", "Run Time: " + runtime.toString());
//            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
//            telemetry.update();
//        }
    }
}
