const path = require('path');
const solc = require('solc');
const fs = require('fs-extra');

//get build folder path
const buildPath = path.resolve(__dirname, 'build');
//remove build folder to remove old if nay
fs.removeSync(buildPath);  //fs-extra helps to recursively remove folders
//get path to all contracts
const contractsPath= path.resolve(__dirname,'contracts', 'StarbucksUsers.sol');
//read contract sol files
const source = fs.readFileSync(contractsPath, 'utf8');
//compile contract files read by solc. Only contracts are extracted to outputs
const outputs = solc.compile(source, 1).contracts;
//rebuild build folder if it doesn't exist (should have been deleted)
fs.ensureDirSync(buildPath);

for (let contract in outputs){
  fs.outputJSONSync(
    path.resolve(buildPath, contract.replace(':','')+'.json'),
    //path.resolve(buildPath, contract, '.json'),  result in contract/.json
    outputs[contract]
  );
}