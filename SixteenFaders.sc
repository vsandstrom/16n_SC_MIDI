/* 
*			16n Supercollider Class 
*/

SixteenFaders {
	classvar func;
    classvar physical = false;
	var <fader, 
    var enable = true;

	*new {
		^super.new.init();
	}

	init {
		var found = "16n found";
        var server = Server.default;
		var id = 0;
		var midilist = [];
		MIDIClient.init();
		MIDIIn.connectAll;
		midilist = MIDIClient.sources;

		// Check if 16n is in MIDIEndPoints, store uid in global var.
		(
			for(0, midilist.size - 1){|i|
				if (midilist[i].device == "16n", {
					id = midilist[i].uid;
					MIDIIn.connectAll;
					found.postln;
                    physical = true;
				    };
                )};
            );
        );

		// MIDIdef with correct midi \uid (srcID)
		(
			fader = 16.collect{
				Bus.control(server, 1);
			};

			if (func.isNil) {
                if (physical) {
                    func = MIDIFunc.new({
                        |val, num, chan, src|
                        if (enable) {
                            ("***	Fader: " ++ '[ ' ++ (num - 32) ++ ' ]' ++ 
                            "	Value: " ++ '[ ' ++ val ++ ' ]').postln;
                        };

                        switch(num, 
                            32, { fader[0].set(val.linlin(0,127,0,1)) },
                            33, { fader[1].set(val.linlin(0,127,0,1)) },
                            34, { fader[2].set(val.linlin(0,127,0,1)) },
                            35, { fader[3].set(val.linlin(0,127,0,1)) },
                            36, { fader[4].set(val.linlin(0,127,0,1)) },
                            37, { fader[5].set(val.linlin(0,127,0,1)) },
                            38, { fader[6].set(val.linlin(0,127,0,1)) },
                            39, { fader[7].set(val.linlin(0,127,0,1)) },
                            40, { fader[8].set(val.linlin(0,127,0,1)) },
                            41, { fader[9].set(val.linlin(0,127,0,1)) },
                            42, { fader[10].set(val.linlin(0,127,0,1)) },
                            43, { fader[11].set(val.linlin(0,127,0,1)) },
                            44, { fader[12].set(val.linlin(0,127,0,1)) },
                            45, { fader[13].set(val.linlin(0,127,0,1)) },
                            46, { fader[14].set(val.linlin(0,127,0,1)) },
                            47, { fader[15].set(val.linlin(0,127,0,1)) },

                        )			
                    }, msgNum: Array.series(16,32,1), chan: 0, msgType: \control, srcID: id);

                } {
                    var window = Window.new("SixteenFaders", 1000@500, true, true, server, false).front;
                    var sliders = 16.collect({|i|
                        Slider(window, Rect(20, 50, 400, 20))
                        // .action_({
                        //     fader[i].set(sliders[i].value);
                        //
                        // })

                    })


                }

			}

		);
	}

    faderAt {|faderPosition|
        ^fader[faderPosition];

    }

	enablePost {
		enable = true;
	}

	disablePost {
		enable = false;
	}

	usage { 
      var usage = "Usage: A 'Bus' object is accessed by <instance of object>.fader[n] or \n<instance of object>.faderAt(n). 'n' is the corresponding fader number, \nbut zero-indexed. ('0' gets you the first fader)\n";
        Post << "|----------------------------------------------------------------------//\n" << usage << "|----------------------------------------------------------------------//\n";
	}
}
