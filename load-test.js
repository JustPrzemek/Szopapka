import http from 'k6/http';
import { check } from 'k6';

export const options = {
  scenarios: {
    constant_load: {
      executor: 'constant-vus',
      vus: 10,
      duration: '5m', 
    },
  },
};

const firebaseKey = __ENV.FIREBASE_API_KEY;
const testEmail = __ENV.FIREBASE_TEST_EMAIL;
const testPassword = __ENV.FIREBASE_TEST_PASSWORD;

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

  const res = http.get(
    'https://szopapka-backend.azurewebsites.net/api/Family/getFamilyWithMembers',
    {
      headers: {
        'Authorization': `Bearer ${authToken}`,
        'Content-Type': 'application/json'
      }
    }
  );
  check(res, { 'status was 200': (r) => r.status === 200 });
}
