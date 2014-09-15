/**
 *  sharp.aquos
 *
 *  Copyright 2014 Gavin Mogan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
 
preferences {
    input("username", "text", title: "Username", description: "Your TV username")
    input("password", "password", title: "Password", description: "Your TV password")
}

metadata {
	definition (name: "sharp.aquos", namespace: "halkeye", author: "Gavin Mogan") {
		capability "Switch"
		capability "Polling"
		capability "Refresh"
        
        capability "Actuator"
		capability "Sensor"
   	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "on", label: '${name}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
			state "off", label: '${name}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
		}
		standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}

		main "switch"
		details(["switch","refresh"])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'switch' attribute

}

// handle commands
def on() {
	log.debug "Executing 'on'"
	// TODO: handle 'on' command
    new physicalgraph.device.HubAction("${settings.username}\r${settings.password}\rPOWR1   \r", physicalgraph.device.Protocol.LAN)
}

def off() {
	log.debug "Executing 'off'"
    // TODO: handle 'off' command
    new physicalgraph.device.HubAction("${settings.username}\r${settings.password}\rPOWR0   \r", physicalgraph.device.Protocol.LAN)
}

def poll() {
	log.debug "Executing 'poll'"
    def pollAction
    
    pollAction = new physicalgraph.device.HubAction("${settings.username}\r${settings.password}\rPOWR?   \r\r\r\r\r\r\r\r", physicalgraph.device.Protocol.LAN)
	log.debug "pollAction: ${pollAction} - ${pollAction.getProperties()}"
	return pollAction
}

def refresh() {
	log.debug "Executing 'refresh'"
	// TODO: handle 'refresh' command
    new physicalgraph.device.HubAction("${settings.username}\r${settings.password}\rPOWR?   \r", physicalgraph.device.Protocol.LAN)
}


