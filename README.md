# ArmEmulator
This repo is for our CS4488 Capstone Project, and Simplified ARM style assembly Emulator Geared towards educational purposes.

To run the application, clone the repo and use the command "gradle run" while in the project directory (inside a shell). You may also download the source code via a release. 



## Memebers
Traae Bloxham - System Architecture Implementation

Daniel Igbokwe - Instruction Translation

David Hellwig - UI Design 

Thomas Neyman - Instruction Set Implementation


## Our Assignment Prompt

###CS4488 Fall 2022 Project: ISU ARM360 Assembly/Machine Code Emulator

Implement a VERY simple emulator for a minimalist subset of ARM assembly, similar to a 16bit version of the instruction set on an IBM 360 or 1130. The emulator will be similar to the Marie, which I suggest checking out.  This assembly emulator could be used in CS1337 to teach assembly and machine code.

The user provides a .txt file containing the assembly code. The emulator translates each assembly instruction into a 4Hex digit machine code instruction with the program being loaded starting at memory location 0, and the memory for the program beginning after the last assembly instruction. The display shows the assembly program, the translated machine code, in the first column of the memory, the 16 (or 4 if you think better) registers, and the program counter. The program counter is initially set to zero, which is the first assembly instruction to be executed.   When the “next” instruction button is pressed, the emulator executes the instruction indicated in the program counter and the resulting memory is displayed in the next column to the right with the instruction that was executed and the next instruction to be executed highlighted. 

Here is one possible subset of ARM, similar to IBM 360 and 1130 assembly, that fits into a 16bit architecture and can be used with 256 bytes of memory. This will allow the needed information for the emulation to be displayed on a computer screen in a manner that is comprehensible to the user. (not all the memory will be visible without scrolling, but that is ok.) You may decide to alter the assembly belor or replace it with something better. Memory addresses are 2 Hex digits (0-255). 16 registers so need 1 hex digit to address a register. Memory is 16 bits and can store an address, a signed int16, or an instruction. Initially use hard addresses both for any array type access (increment a pointer) and for branching. You could add a base-register offset gadget eventually. If there is any way to add labels for branching, that would be better

(Note: the table hasn't been formatted for markdown.

|Inst	 OPCode	Operand	Operand	Operand
1.	Halt 		0	
2.	Load		1	<Hex mem> 	<1Hex reg>
3.	Store		2	<Hex mem>	<1Hex reg>
4.	Add		3	<1Hex reg>	<1Hex reg>	<1Hex reg>	//or could replace 2nd reg content
5.	Subt		4	<1Hex reg>	<1Hex reg>	<1Hex reg>	
6.	Mult		5	<1Hex reg>	<1Hex reg>	<1Hex reg>    
7.	IntDivide	6	<1Hex reg>	<1Hex reg>	<1Hex reg>
8.	Load Indirect	7	<2Hex mem> 	<1Hex reg>
9.	Store Indirect	8	<2Hex mem> 	<1Hex reg>
10.	Branch		9	<2Hex mem>
11.	BranchNeg	A	<1Hex reg>	<2Hex mem>	
12.	BranchPos	B	<1Hex reg>	<2Hex mem>
13.	LoadConstant	C	<1Hex reg>	need to be able to load -1
14.	ReadInt	D	<1Hex reg>				// reads from textbox into register 
15.	WriteInt	E	<1Hex reg>				// writes from register to label
16.	???		F
