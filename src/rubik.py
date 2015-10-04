#!/usr/bin/env python
import random, argparse
parser = argparse.ArgumentParser()
parser.add_argument("-c", "--cube",
                    dest="c",
                    type=str,
                    help="cube type",
                    metavar="CUBE",
                    required=False,
                    default="rubik"
                    )
parser.add_argument("-l", "--length",
                    dest="l",
                    type=int,
                    help="length of scramble",
                    metavar="LENGTH",
                    required=False,
                    default=25
                    )
parser.add_argument("-i", "--iterations",
                    dest="it",
                    type=int,
                    help="amount of scrambles",
                    metavar="AMOUNT",
                    required=False,
                    default=1
                    )
parser.add_argument("-n", "--notation",
                    dest="n",
                    type=str,
                    help="notation (defaults to SiGN)",
                    metavar="NOTATION",
                    required=False,
                    default="SiGN"
                    )

class Cube:
    defaultmodset = ["", "\'", "2"]
    def __init__(self, ctype):
        if ctype == "rubik":
            self.turnset        =   ["R", "L", "U", "D", "F", "B"]
            self.modifierset    =   self.defaultmodset
            self.name           =   "Rubik's Cube"
        if ctype == "revenge":
            self.turnset        =   ["R", "L", "U", "D", "F", "B", "2R", "2L", "2U", "2D", "2F", "2B", "r", "l", "u", "d", "f", "b"]
            self.modifierset    =   self.defaultmodset
            self.name           =   "Rubik's Revenge (4x4x4)"
        if ctype == "professor":
            self.turnset        =   ["R", "L", "U", "D", "F", "B", "2R", "2L", "2U", "2D", "2F", "2B", "r", "l", "u", "d", "f", "b"]
            self.modifierset    =   self.defaultmodset
            self.name           =   "Professor Cube (5x5x5)"
        if ctype == "skewb":
            self.turnset        =   ["F", "R", "B", "L"]
            self.modifierset    =   self.defaultmodset
            self.name           =   "Skewb"
        if ctype == "megaminx":
            self.turnset        =   ["R++", "R--", "D++", "D--", "R++", "R--", "D++", "D--", "U"]
            self.modifierset    =   [""]
            self.name           =   "Megaminx"
a = parser.parse_args()
length = a.l
turnModifiers = ["", "\'", "2"]
alg = []
for i in xrange(a.it):
    del alg[:]
    if a.c == "megaminx":
        isMinx = True
    else:
        isMinx = False
    cube = Cube(a.c)
    #print cube.turnset
    #print cube.modifierset
    print "Generating %s scramble(s) for cube type \"%s\"" %(str(a.it), cube.name)
    #exit(1)
    for i in xrange(length):
        if alg == []:
            if isMinx: alg.append('')
            currentTurn = []
            currentTurn.append(random.choice(cube.turnset))
            currentTurn.append(random.choice(cube.modifierset))
            alg.append("".join(currentTurn))
        else:
            lastTurn = alg[-1]
            cutUp = list(lastTurn)
            lTDirection = cutUp[0]
            if isMinx: lTDirection = lastTurn
            currentTurn = []
            cTDirection = random.choice(cube.turnset)
            cTModifier = random.choice(cube.modifierset)
            while cTDirection.upper() == lTDirection.upper():
                cTDirection = random.choice(cube.turnset)
            currentTurn.append(cTDirection)
            currentTurn.append(cTModifier)
            alg.append("".join(currentTurn))
            if not bool(i%5):
                if isMinx: alg.append('\n')
    print " ".join(alg)
