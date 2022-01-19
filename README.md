# SixteenFaders
16n MIDI Controller Template

Midi-controller help class for the 16n Faderbank.
```supercollider
f = SixteenFaders.new;
```
## Methods:

The class detects if there is a Faderbank connected to the computer and makes each fader available as a control bus through either:
```supercollider
f.fader[n];
```
or
```supercollider
f.faderAt(n);
```
The "fader" array, containing each of the 16 control busses, are zero-indexed.
To get the control bus corresponding to the first fader, it is at position [0].
```supercollider
f.enablePost; / f.disablePost;
```
Enables / disables the printing of every value- and fader change made.
```supercollider
f.usage;
```
Posts a helpful "usage" message about the class. Can also be used as class method.
