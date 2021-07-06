metadata {
	definition (name: "Tank Utility", namespace: "imnotbob" , author: "Eric S") {
		capability "Energy Meter"
		capability "Polling"
		capability "Refresh"
		capability "Sensor"
		capability "Power Meter"
		capability "Temperature Measurement"

		attribute "lastreading", "date"
		attribute "capacity", "number"
		attribute "level", "number"
	}

/*
	// UI tile definitions
	tiles (scale: 2){
        multiAttributeTile(name:"level", type: "generic"){
            tileAttribute("device.level", key: "PRIMARY_CONTROL") {
                attributeState("device.level",label:'${currentValue}%', backgroundColors:[
                    [value: 30, color: "#bc2323"],
                    [value: 40, color: "#d04e00"],
                    [value: 50, color: "#f1d801"],
                    [value: 60, color: "#44b621"],
                    [value: 70, color: "#90d2a7"],
                    [value: 80, color: "#1e9cbb"],
                    [value: 90, color: "#153591"]
                ])
            }
            tileAttribute("device.lastreading", key: "SECONDARY_CONTROL") {
                attributeState("default", label:'Last Reading was at ${currentValue}', icon: "st.Health & Wellness.health9")
            }
        }
        
		valueTile("capacity", "device.capacity", width: 6, height: 2) {
			state("default", label:'Tank capacity ${currentValue} Gal')
		}          
		
		valueTile("temperature", "device.temperature", width: 2, height: 2) {
			state("device.temperature", label:'${currentValue} F' )
		}      
		        
		valueTile("refresh", "command.refresh", width: 2, height: 2) {
			state "default", label:'refresh', action:"refresh.refresh", icon:"st.secondary.refresh-icon"
		}
        
		main(["level"])
        
		details(["level", "capacity", "refresh","temperature"])
	}
*/
}
    
void installed() {
	log.debug "installed()"
	// The device refreshes every 1 Hour by default so if we miss 2 refreshes we can consider it offline
	// sendEvent(name: "checkInterval", value: 60 * 120, data: [protocol: "cloud"], displayed: false)
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
}

def refresh() {
	log.debug "refresh called"
	poll()
}

void poll() {
	log.debug "Executing 'poll' using parent SmartApp"
	parent.pollChildren()
}

def generateEvent(Map results) {
	results.each { String name, value ->
		sendEvent(name: name, value: value)
	}
}
