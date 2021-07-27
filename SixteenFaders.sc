
/* 
*			16n Supercollider Class 
*/

//  TODO: Swap global variables for getters.
//  TODO: Only allocate Bus.control if called. 

SixteenFaders {
	var cc, val;
	// var val0, val1, val2, val3, val4, val5, val6, val7, val8, val9, val10, val11, val12, val13, val14, val15; 

	// Getters for every cc-value of controller:
	var >val0, >val1, >val2, >val3, >val4, >val5, >val6, >val7, >val8, >val9, >val10, >val11, >val12, >val13, >val14, >val15; 

	
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
			)			
		);

		// MIDIdef with correct midi \uid (srcID)
		(
			MIDIdef.new(\sixteenFaders, {
				|value, num, chan, src|
				cc = num;
				val = value;

				("Fader: " ++ (num - 31) ++ " value: " ++ val).postln;

				switch(num, 
					32, { val0 = val.linlin(0,127,0,1) },
					33, { val1 = val.linlin(0,127,0,1) },
					34, { val2 = val.linlin(0,127,0,1) },
					35, { val3 = val.linlin(0,127,0,1) },
					36, { val4 = val.linlin(0,127,0,1) },
					37, { val5 = val.linlin(0,127,0,1) },
					38, { val6 = val.linlin(0,127,0,1) },
					39, { val7 = val.linlin(0,127,0,1) },
					40, { val8 = val.linlin(0,127,0,1) },
					41, { val9 = val.linlin(0,127,0,1) },
					42, { val10 = val.linlin(0,127,0,1) },
					43, { val11 = val.linlin(0,127,0,1) },
					44, { val12 = val.linlin(0,127,0,1) },
					45, { val13 = val.linlin(0,127,0,1) },
					46, { val14 = val.linlin(0,127,0,1) },
					47, { val15 = val.linlin(0,127,0,1) })

			}, msgNum: Array.series(16,32,1), chan: 0, msgType: \control, srcID: id);
		);	
	}

	usage {
		var usage = "Usage: ~sixteenVal* as Bus.control, where * is number from 0 to 15.";

		usage.postln;
	}

}
