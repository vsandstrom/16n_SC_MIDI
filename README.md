# SixteenFaders
16n MIDI Controller Template

Midi-controller help class for the 16n Faderbank.
```supercollider
f = SixteenFaders.new;
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
