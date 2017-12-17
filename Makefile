run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j test_mac_10.12_safari

test_mac_10.12_safari:
	mvn test -DbrowserName=safari -Dversion=latest -Dplatform="macOS 10.12" -Dsuitename=test_mac_10_12_safari -Dcucumber.options="--tags @rft"


