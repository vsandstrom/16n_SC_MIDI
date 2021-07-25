
/* 
*			16n Supercollider Class 
*/

//  TODO: Swap global variables for getters.
//  TODO: Only allocate Bus.control if called. 

SixteenFaders {

	*new {
	
		^super.new.init(  )
	
	}

	init {
		var found = "16n found";
		var id = 0;
		var midilist = [];
		MIDIClient.init();
		MIDIIn.connectAll;
		midilist = MIDIClient.sources;

		// Check if 16n is in MIDIEndPoints, store uid in global var.
		(
			for(0, midilist.size - 1, {
				arg i;
				if (midilist[i].asString == MIDIEndPoint("16n", "16n").asString, {
					id = midilist[i].uid;
					MIDIIn.connectAll;
					found.postln;
					
					}
				)}
			);
			
		);


		// MIDIdef with correct midi \uid (srcID)
		(
			~sixteenVal0 = Bus.control(Server.default, 1);
			~sixteenVal1 = Bus.control(Server.default, 1);
			~sixteenVal2 = Bus.control(Server.default, 1);
			~sixteenVal3 = Bus.control(Server.default, 1);
			~sixteenVal4 = Bus.control(Server.default, 1);
			~sixteenVal5 = Bus.control(Server.default, 1);
			~sixteenVal6 = Bus.control(Server.default, 1);
			~sixteenVal7 = Bus.control(Server.default, 1);
			~sixteenVal8 = Bus.control(Server.default, 1);
			~sixteenVal9 = Bus.control(Server.default, 1);
			~sixteenVal10 = Bus.control(Server.default, 1);
			~sixteenVal11 = Bus.control(Server.default, 1);
			~sixteenVal12 = Bus.control(Server.default, 1);
			~sixteenVal13 = Bus.control(Server.default, 1);
			~sixteenVal14 = Bus.control(Server.default, 1);
			~sixteenVal15 = Bus.control(Server.default, 1);

			MIDIdef.new(\sixteenFaders, {
				|val, num, chan, src|
				("Fader: " ++ (num - 31) ++ " value: " ++ val).postln;

				switch(num, 
					32, { ~sixteenVal0.set(val.linlin(0,127,0,1)) },
					33, { ~sixteenVal1.set(val.linlin(0,127,0,1)) },
					34, { ~sixteenVal2.set(val.linlin(0,127,0,1)) },
					35, { ~sixteenVal3.set(val.linlin(0,127,0,1)) },
					36, { ~sixteenVal4.set(val.linlin(0,127,0,1)) },
					37, { ~sixteenVal5.set(val.linlin(0,127,0,1)) },
					38, { ~sixteenVal6.set(val.linlin(0,127,0,1)) },
					39, { ~sixteenVal7.set(val.linlin(0,127,0,1)) },
					40, { ~sixteenVal8.set(val.linlin(0,127,0,1)) },
					41, { ~sixteenVal9.set(val.linlin(0,127,0,1)) },
					42, { ~sixteenVal10.set(val.linlin(0,127,0,1)) },
					43, { ~sixteenVal11.set(val.linlin(0,127,0,1)) },
					44, { ~sixteenVal12.set(val.linlin(0,127,0,1)) },
					45, { ~sixteenVal13.set(val.linlin(0,127,0,1)) },
					46, { ~sixteenVal14.set(val.linlin(0,127,0,1)) },
					47, { ~sixteenVal15.set(val.linlin(0,127,0,1)) },

				)			
			}, msgNum: Array.series(16,32,1), chan: 0, msgType: \control, srcID: id);
		);
		
	}

	usage {
		var usage = "Usage: ~sixteenVal* as Bus.control, where * is number from 0 to 15.";

		usage.postln;
	}

}
