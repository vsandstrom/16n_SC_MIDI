/* 
*			16n controller MIDI 2 SC template
*/

// TODO: create Gui that follows the fader input.

MIDIClient.init();
MIDIIn.connectAll;
~midiList = MIDIClient.sources;

// Check if 16n is in MIDIEndPoints, store uid in global var.
(
	for(0, ~midiList.size - 1, {
		arg i;
		if (~midiList[i].asString == MIDIEndPoint("16n", "16n").asString, {
			~sixTeeN = ~midiList[i].uid;
			MIDIIn.connectAll;
			"16n found".postln;
			},
		)}
	);
)


// MIDIdef with correct midi \uid (srcID)
	(
		~sixteenVal0 = Bus.control(s, 1);
		~sixteenVal1 = Bus.control(s, 1);
		~sixteenVal2 = Bus.control(s, 1);
		~sixteenVal3 = Bus.control(s, 1);
		~sixteenVal4 = Bus.control(s, 1);
		~sixteenVal5 = Bus.control(s, 1);
		~sixteenVal6 = Bus.control(s, 1);
		~sixteenVal7 = Bus.control(s, 1);
		~sixteenVal8 = Bus.control(s, 1);
		~sixteenVal9 = Bus.control(s, 1);
		~sixteenVal10 = Bus.control(s, 1);
		~sixteenVal11 = Bus.control(s, 1);
		~sixteenVal12 = Bus.control(s, 1);
		~sixteenVal13 = Bus.control(s, 1);
		~sixteenVal14 = Bus.control(s, 1);
		~sixteenVal15 = Bus.control(s, 1);

		MIDIdef.new(\sixteenN, {
			|val, num, chan, src|
			("cc: " ++ num ++ " " ++ val).postln;

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
			
		}, msgNum: Array.series(16,32,1), chan: 0, msgType: \control, srcID: ~sixTeeN);
	);
