import { Component, OnInit } from '@angular/core';
import { Client, CompatClient, IFrame, Message, Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'This is WebSocket Test'
  greetings: string[] = [];
  disabled = true;
  private stompClient?: CompatClient;

  userForm: FormGroup = this.formBuilder.group({
    userName: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder) { }

  // ngOnInit(): void {
  //   this.userForm = this.formBuilder.group({
  //     userName: ['', Validators.required]
  //   });
  // }

  connectToWebsocketWithStomp() {
    console.log('call connect to server')
    const socket = new SockJS('http://localhost:8080/onlyfullstack-stomp-endpoint');
    this.stompClient = Stomp.over(socket)

    const _this = this;
    this.stompClient.connect({}, function(frame: IFrame) {
      _this.showUserNameForm(true);
      console.log('Connected: ' + frame);

      _this.stompClient!.subscribe('/topic/hi', function(hello) {
        _this.showGreeting(JSON.parse(hello.body).greeting);
      });
    });


  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.showUserNameForm(false);
    console.log('Disconnected!');
  }

  submit() {
    this.stompClient!.send(
      '/onlyfullstack/hello',
      {},
      JSON.stringify({ name: this.userForm.value.userName })
    );
  }

  showGreeting(message: string) {
    this.greetings.push(message);
  }

  showUserNameForm(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }
}
