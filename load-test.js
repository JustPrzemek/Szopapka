import http from 'k6/http';
import { check } from 'k6';

export const options = {
  vus: 5,
  duration: '30s'
};

const testEmail = __ENV.FIREBASE_API_KEY;
const testPassword = __ENV.FIREBASE_TEST_EMAIL;
const firebaseKey = __ENV.FIREBASE_TEST_PASSWORD;

export default function () {

  const loginRes = http.post(
    `https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${firebaseKey}`,
    JSON.stringify({
      email: testEmail,
      password: testPassword,
      returnSecureToken: true
    }),
    { headers: { 'Content-Type': 'application/json' } }
  );

  const authToken = loginRes.json().idToken;


  const headers = {
    'Authorization': `Bearer ${authToken}`,
    'Content-Type': 'application/json'
  };


  const res = http.get(
    'https://szopapka-backend.azurewebsites.net/api/Family/getFamilyWithMembers',
    { headers }
  );
  check(res, { 'status was 200': (r) => r.status === 200 });
}
