#!/bin/bash

#DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
#python ${DIR}/reference_solution.py

cd src
java -cp . main/Engine
