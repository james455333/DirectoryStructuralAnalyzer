# show your directory structure with Folder Structure

#### example
```
example
|— first-1.txt
|— first-2.txt
|— firstLayer-1
|   |— second-1.txt
|   |— second-2.txt
|   |— secondLayer-1
|   |   |— third-1.txt
|   |— secondLayer-2
|— firstLayer-2
|   |— second-1.txt
|   |— second-2.txt
```


### step 1 : compiled executable jar file
```mvn
mvn clean package
```

### step 2 : execute jar file with arguments
#### at this point，we have only two arguments
| name | argument  | requirement | descript                                                 |
|------|-----------|-------------|----------------------------------------------------------|
| HELP | -h, --help| optional    | get program info and help                                |
| PATH | -p, --path| required    | the path you want to analyze, **must be directory** and **absolute path** |

#### example
`java -jar DirectoryStructuralAnalyzer-1-0-0.jar -p c:\example`

`java -jar DirectoryStructuralAnalyzer-1-0-0.jar -p /var/www/html`

## future update
- [ ] add argument : export output.txt path (or default export to commend execute directory)
- [ ] add argument : max deep structure limit(default -1, show all)
- [ ] add argument : max file show limit for each folder(default -1, show all)
- [ ] add argument : filename and file type regex filter(default *, show all)
