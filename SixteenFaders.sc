/* 
*			16n Supercollider Class 
*/

SixteenFaders {
	classvar func = nil;
	var <fader;
    var enable = true;
    var physical = true;
    var id;
    var midinum = 32;

	*new {
		^super.new.init();
	}

	init {
		var found = "16n found";
        var server = Server.default;
		var sources;
		MIDIClient.init();
		MIDIIn.connectAll;
		sources = MIDIClient.sources;

		// Check if 16n is in MIDIEndPoints, store uid in global var.
		(
        sources.do{|source|
            if (source.device == "16n") {
                id = source.uid;
                found.postln;
                physical = true;
                };
            };
		);

		// MIDIdef with correct midi \uid (srcID)
		(
			fader = 16.collect{
				Bus.control(server, 1);
			};

			if (func.isNil == false) {
                // free existing midifunc
                func.free;
            };

            if (physical) {
                func = MIDIFunc.new({
                    |val, num, chan, src|
                    if (enable) {
                        ("***	Fader: " ++ '[ ' ++ (num - midinum) ++ ' ]' ++ 
                        "	Value: " ++ '[ ' ++ val ++ ' ]').postln;
                    };

                    if (num.inclusivelyBetween(midinum, midinum+15)) {
                        fader[num - midinum].set(val / 127);

                    }
                }, msgNum: Array.series(16,midinum,1), chan: 0, msgType: \control, srcID: id);
            } {
                postln("No 16n was found, no MIDIFunc created");
                // TODO: Create a mock QT faderbank for testing
            }
		);
	}

    setMidiChannel {|num|
        midinum = num;
        this.init;
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
