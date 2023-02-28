# SixteenFaders
16n MIDI Controller Template

Midi-controller help class for the 16n Faderbank.
<br>
Example:
```supercollider

SynthDef(\test, {
    var sig, env, verb;
    sig = (SinOsc.ar(\freq.kr(300)) * \vol.kr(0))!2;
    env = EnvGen.kr(
        Env([0,1,0], [\atk.kr(0.1) / 2, \rel.kr(1) * 2], [-4, 4]),
        \trig.kr(1), doneAction: 2
    );
    verb = NHHall.ar(sig) * \verbAmount.kr(0);
    Out.ar(0, (sig + verb) * env );
}).add;

Synth(\test, [\vol, f.faderAt(0).asMap, \verb, f.faderAt(1)]);

fork{
    var s;
    loop{
        s = Synth(\test, [
            \vol, f.faderAt(0).asMap,
            \verbAmount, f.faderAt(1).asMap,
            \atk, f.faderAt(2).asMap,
            \rel, f.faderAt(3).asMap,
        ]);
        // wait(time);
        wait(f.faderAt(4).getSynchronous.linexp(0, 1, 0.1, 2));
    }
}
```
When evaluated, it detects if there is a Faderbank connected to the computer and populates a control bus for each fader.

## Methods:
The returned object makes each control bus available through either:<br>
<br>
```supercollider
// member variable "fader"
f.fader[n];

```
or
```supercollider
// instance method "faderAt()"
f.faderAt(n);
```
The "fader" array, containing each of the 16 control busses, are zero-indexed.
To get the control bus corresponding to the first fader, it is at position [0].<br>
<br>
```supercollider
f.enablePost; / f.disablePost;
```
Enables / disables the printing of every value- and fader change made.<br>
<br>
```supercollider
f.usage;
```
Posts a helpful "usage" message about the class. Can also be used as class method.
<br>
